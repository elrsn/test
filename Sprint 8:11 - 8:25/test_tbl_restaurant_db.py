# Author: Elliott Larsen
# Date: 8/24/2022
# Description: This is a test file for the restaurant table.


import datetime
import decimal
from data_generators.gen_address import gen_address
from data_generators.gen_restaurant import gen_restaurant
from connection import connection

def test_db_connection():
    """
    Tests the SQL Database connection.  
    """
    assert connection.is_connected() == True

def test_getting_all_tables():
    """
    Tests if all tables can be called.
    """
    cursor = connection.cursor()
    query = "SHOW TABLES"
    cursor.execute(query)
    table_list = []
    for tab in cursor:
        table_list.append(tab)
    assert len(table_list) == 16

def generate_address_db(num_restaurant):
    """
    This function takes an integer as parameter and generates tbl_address.
    """
    address_lst = gen_address(num_restaurant)
    cursor = connection.cursor()
    query = "INSERT INTO tbl_address(latitude, longitude, zip_code, country, city, state, street_address) VALUES(%s, %s, %s, %s, %s, %s, %s)"
    for i in address_lst:
        cursor.execute(query, i)
        connection.commit()

num_restaurant = 5
generate_address_db(num_restaurant)
restaurant_lst = gen_restaurant(num_restaurant)
# Create
def test_create_restaurants():
    """
    Create multiple restaurants using randomly generated data and test whether it persist to the database or not.
    """
    cursor = connection.cursor()
    query = "INSERT INTO tbl_restaurant(name, address_id, phone_no, website, open_time, close_time, rating, rating_count) VALUES(%s, %s, %s, %s, %s, %s, %s, %s)"
    
    for i in restaurant_lst:
        cursor.execute(query, i)
        connection.commit()
    
    query = "SELECT * FROM tbl_restaurant"
    cursor.execute(query)
    result = list(cursor.fetchall())

    assert len(result) == num_restaurant

# Read
def test_read_restaurant():
    """
    Test reading from tbl_restaurant.
    """
    status = False
    cursor = connection.cursor()
    query = "SELECT * FROM tbl_restaurant"
    cursor.execute(query)
    result = list(cursor.fetchall())
    for i in range(len(result)):
        result[i] = list(result[i])
        del result[i][0]
        result[i] = result[i]
    
    for i in range(num_restaurant):
        for j in range(len(result[i])):
            if type(result[i][j]) == datetime.timedelta or type(result[i][j]) == decimal.Decimal:
                continue
            elif result[i][j] != restaurant_lst[i][j]:
                status = False
            else:
                status = True

    assert status

# Update
def test_update_restaurant():
    """
    Test updating tbl_restaurant.
    """
    new_res_lst = gen_restaurant(num_restaurant)
    cursor = connection.cursor()
    
    for i in range(len(new_res_lst)):
        query = f"UPDATE tbl_restaurant SET name = '{new_res_lst[i][0]}', address_id = '{new_res_lst[i][1]}', phone_no = '{new_res_lst[i][2]}', website = '{new_res_lst[i][3]}' WHERE id = {i + 1}"
        cursor.execute(query)
        connection.commit()
    
    query = "SELECT * FROM tbl_restaurant"
    cursor.execute(query)
    result = list(cursor.fetchall())
    for i in range(len(result)):
        result[i] = list(result[i])
        del result[i][0]
        result[i] = tuple(result[i])

    status = False
    for i in range(num_restaurant):
        for j in range(4):
            if result[i][j] == new_res_lst[i][j]:
                status = True
            else:
                status = False

    return status

# Delete
def test_delete_restaurant():
    """
    Test deleting rows in tbl_restaurant.
    """
    cursor = connection.cursor()
    for i in range(1000):
        query = f"DELETE FROM tbl_restaurant WHERE id = {i + 1}"
        cursor.execute(query)
        connection.commit()

    query = "ALTER TABLE tbl_restaurant AUTO_INCREMENT = 1"
    cursor.execute(query)
    connection.commit()

    cursor = connection.cursor()
    for i in range(1000):
        query = f"DELETE FROM tbl_address WHERE id = {i + 1}"
        cursor.execute(query)
        connection.commit()

    query = "ALTER TABLE tbl_address AUTO_INCREMENT = 1"
    cursor.execute(query)
    connection.commit()

    query = "SELECT * FROM tbl_restaurant"
    cursor.execute(query)
    res_delete_result = list(cursor.fetchall())

    query = "SELECT * FROM tbl_address"
    cursor.execute(query)
    address_delete_result = list(cursor.fetchall())

    assert len(res_delete_result) == 0 and len(address_delete_result) == 0