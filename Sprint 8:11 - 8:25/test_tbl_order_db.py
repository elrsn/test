# Author: Elliott Larsen
# Date: 8/24/2022
# Description: This is a test file for the order table.

#----------------------------------------------------------------------------------
#CREATE TABLE IF NOT EXISTS tbl_order(
#    id INT AUTO_INCREMENT NOT NULL,
#    driver_id INT NOT NULL,
#    customer_id INT NOT NULL,
#    restaurant_id INT NOT NULL,
#    discount_id INT,
#    status ENUM('pending', 'accepted', 'working', 'waiting', 'delivery', 'delivered', 'cancelled', 'error') NOT NULL,
#    total_price DECIMAL NOT NULL,
#    PRIMARY KEY (id),
#    FOREIGN KEY (driver_id) REFERENCES tbl_driver(account_id),
#    FOREIGN KEY (customer_id) REFERENCES tbl_customer(account_id),
#    FOREIGN KEY (restaurant_id) REFERENCES tbl_restaurant(id),
#    FOREIGN KEY (discount_id) REFERENCES tbl_discount(id)
#);
#----------------------------------------------------------------------------------

from data_generators.gen_discount import gen_discount
from data_generators.gen_account import gen_account
from data_generators.gen_address import gen_address
from data_generators.gen_order import gen_order
from data_generators.gen_restaurant import gen_restaurant
from data_generators.gen_customer import gen_customer
from data_generators.gen_driver import gen_driver
from connection import connection

driver_num = 5
customer_num = 5
address_num = 10
restaurant_num = address_num
discount_num = 5

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

def generate_tbl_discount():
    """
    Generate tbl_discount.
    """
    discount_lst = gen_discount(discount_num)
    cursor = connection.cursor()
    query = "INSERT INTO tbl_discount(code, percent, value) VALUES(%s, %s, %s)"
    
    for i in discount_lst:
        cursor.execute(query, i)
        connection.commit()

def cleanup_tbl_discount():
    """
    Delete rows in tbl_discount.
    """
    cursor = connection.cursor()
    for i in range(100):
        query = f"DELETE FROM tbl_discount WHERE id = {i + 1}"
        cursor.execute(query)
        connection.commit()

    query = "ALTER TABLE tbl_discount AUTO_INCREMENT = 1"
    cursor.execute(query)
    connection.commit()

def generate_customer_tbl_acct():
    """
    Generate customer accounts.
    """
    customers = gen_account(customer_num, "C")
    cursor = connection.cursor()
    query = "INSERT INTO tbl_account(first_name, last_name, email, phone_no, password, type) VALUES(%s, %s, %s, %s, %s, %s)"
    
    for i in customers:
        cursor.execute(query, i)
        connection.commit()

def generate_driver_tbl_acct():
    """
    Generate driver accounts.
    """
    drivers = gen_account(driver_num, "D")
    cursor = connection.cursor()
    query = "INSERT INTO tbl_account(first_name, last_name, email, phone_no, password, type) VALUES(%s, %s, %s, %s, %s, %s)"
    
    for i in drivers:
        cursor.execute(query, i)
        connection.commit()

def cleanup_tbl_account():
    """
    Delete rows in tbl_account.
    """
    cursor = connection.cursor()
    for i in range(100):
        query = f"DELETE FROM tbl_account WHERE id = {i + 1}"
        cursor.execute(query)
        connection.commit()

    query = "ALTER TABLE tbl_account AUTO_INCREMENT = 1"
    cursor.execute(query)
    connection.commit()


def generate_tbl_address():
    """
    Generate tbl_address.
    """
    address = gen_address(address_num)
    cursor = connection.cursor()
    query = "INSERT INTO tbl_address(latitude, longitude, zip_code, country, city, state, street_address) VALUES(%s, %s, %s, %s, %s, %s, %s)"
    for i in address:
        cursor.execute(query, i)
        connection.commit()

def cleanup_tbl_address():
    """
    Delete rows in tbl_address.
    """
    cursor = connection.cursor()
    for i in range(100):
        query = f"DELETE FROM tbl_address WHERE id = {i + 1}"
        cursor.execute(query)
        connection.commit()

    query = "ALTER TABLE tbl_address AUTO_INCREMENT = 1"
    cursor.execute(query)
    connection.commit()

def generate_tbl_restaurant():
    """
    Generate tbl_restaurant.
    """
    restaurants = gen_restaurant(restaurant_num)
    cursor = connection.cursor()
    query = "INSERT INTO tbl_restaurant(name, address_id, phone_no, website, open_time, close_time, rating, rating_count) VALUES(%s, %s, %s, %s, %s, %s, %s, %s)"
    
    for i in restaurants:
        cursor.execute(query, i)
        connection.commit()

def cleanup_tbl_restaurant():
    """
    Delete rows in tbl_restaurant.
    """
    cursor = connection.cursor()
    for i in range(100):
        query = f"DELETE FROM tbl_restaurant WHERE id = {i + 1}"
        cursor.execute(query)
        connection.commit()

    query = "ALTER TABLE tbl_restaurant AUTO_INCREMENT = 1"
    cursor.execute(query)
    connection.commit()

def generate_tbl_customer():
    """
    Generate tbl_customer.
    """
    customer_lst = gen_customer(customer_num)
    cursor = connection.cursor()
    query = "INSERT INTO tbl_customer(account_id, address_id) VALUES(%s, %s)"
    
    for i in customer_lst:
        cursor.execute(query, i)
        connection.commit()

def cleanup_tbl_customer():
    """
    Delete rows in tbl_customer.
    """
    cursor = connection.cursor()
    for i in range(100):
        query = f"DELETE FROM tbl_customer WHERE account_id = {i + 1}"
        cursor.execute(query)
        connection.commit()

    query = "ALTER TABLE tbl_customer AUTO_INCREMENT = 1"
    cursor.execute(query)
    connection.commit()

def generate_tbl_driver():
    """
    Generate tbl_driver.
    """
    driver_lst = gen_driver(customer_num, driver_num)
    cursor = connection.cursor()
    query = "INSERT INTO tbl_driver(account_id, license_plate, rating) VALUES(%s, %s, %s)"
    for i in driver_lst:
        cursor.execute(query, i)
        connection.commit()

def cleanup_tbl_driver():
    """
    Delete rows in tbl_driver.
    """
    cursor = connection.cursor()
    for i in range(100):
        query = f"DELETE FROM tbl_driver WHERE account_id = {i + 1}"
        cursor.execute(query)
        connection.commit()


def generate_base_tbls():
    """
    This function generates base tables for tbl_order.
    """
    generate_tbl_discount()
    generate_customer_tbl_acct()
    generate_driver_tbl_acct()
    generate_tbl_address()
    generate_tbl_restaurant()
    generate_tbl_customer()
    generate_tbl_driver()

def cleanup_base_tbls():
    """
    This function cleans up base tables used for tbl_order.
    """
    cleanup_tbl_discount()
    cleanup_tbl_customer()
    cleanup_tbl_driver()
    cleanup_tbl_account()
    cleanup_tbl_restaurant()
    cleanup_tbl_address()

#generate_base_tbls()
order_num = 10
order_lst = gen_order(order_num, driver_num, customer_num, restaurant_num, discount_num)

# Create
def test_create_order():
    """
    Test whether creating and persisting order to tbl_order is successful.
    """
    cursor = connection.cursor()
    query = "INSERT INTO tbl_order(driver_id, customer_id, restaurant_id, discount_id, status, total_price) VALUES(%s, %s, %s, %s, %s, %s)"
    
    for i in order_lst:
        cursor.execute(query, i)
        connection.commit()

    query = "SELECT * FROM tbl_restaurant"
    cursor.execute(query)
    result = list(cursor.fetchall())

    assert len(result) == order_num

# Read
def test_read_order():
    """
    Test reading data from tbl_order.
    """
    cursor = connection.cursor()
    query = "SELECT * FROM tbl_order"
    cursor.execute(query)
    result = list(cursor.fetchall())
    for i in range(len(result)):
        result[i] = list(result[i])
        del result[i][0]
        del result[i][-1]
        result[i] = tuple(result[i])

    for i in range(len(order_lst)):
        order_lst[i] = list(order_lst[i])
        del order_lst[i][-1]
        order_lst[i] = tuple(order_lst[i])
    
    assert result == order_lst
    
# Update
def test_update_order():
    """
    Test updaing tbl_order.
    """
    rand_order_lst = gen_order(order_num, driver_num, customer_num, restaurant_num, discount_num)
    cursor = connection.cursor()
    
    for i in range(len(rand_order_lst)):
        query = f"UPDATE tbl_order SET driver_id = '{rand_order_lst[i][0]}', customer_id = '{rand_order_lst[i][1]}', restaurant_id = '{rand_order_lst[i][2]}', discount_id = '{rand_order_lst[i][3]}', status = '{rand_order_lst[i][4]}' WHERE id = {i + 1}"
        cursor.execute(query)
        connection.commit()
    
    cursor = connection.cursor()
    query = "SELECT * FROM tbl_order"
    cursor.execute(query)
    result = list(cursor.fetchall())
    for i in range(len(result)):
        result[i] = list(result[i])
        del result[i][0]
        del result[i][-1]
        result[i] = tuple(result[i])

    for i in range(len(rand_order_lst)):
        rand_order_lst[i] = list(rand_order_lst[i])
        del rand_order_lst[i][-1]
        rand_order_lst[i] = tuple(rand_order_lst[i])

    assert result == rand_order_lst

# Delete
def test_delete_order():
    """
    Test deleting rows in tbl_order.
    """
    cursor = connection.cursor()
    for i in range(10):
        query = f"DELETE FROM tbl_order WHERE id = {i + 1}"
        cursor.execute(query)
        connection.commit()

    query = "ALTER TABLE tbl_order AUTO_INCREMENT = 1"
    cursor.execute(query)
    connection.commit()

    cursor = connection.cursor()
    query = "SELECT * FROM tbl_order"
    cursor.execute(query)
    result = list(cursor.fetchall())

    assert len(result) == 0

cleanup_base_tbls()

