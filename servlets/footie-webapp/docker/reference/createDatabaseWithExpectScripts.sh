#!/usr/bin/expect

set timeout 60

spawn sh -c "mysql -u root -p"

expect "assword:"
send -- "root\r"

expect ""
send -- "CREATE DATABASE footiedb;\r"

expect ""
send -- "exit\r"

system "mysql -u root -h localhost -proot footiedb < footiedb_User.sql"

close


