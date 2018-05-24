#!/bin/bash

mysql -u root -proot -e "GRANT ALL ON *.* to root@'%' IDENTIFIED BY 'root'"
mysql -u root -proot -e "FLUSH PRIVILEGES"