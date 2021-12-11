/**

-- AUTHORS --
+ Hamza Kamran
+ Adam Gibbons
+ Kimmy Thach

-- DESCRIPTION: --
This is the script that we create the database schema and tables.

-- FUNCTIONAL REQUIREMENT(S) MET: --
+ F.R. 3 CREATE TABLE FUNCTIONS

-- NONFUNCTIONAL REQUIREMENT(S) MET: --
+ None

-- USER INTERFACE REQUIREMENT(S) MET: --
+ None

-- SOFTWARE INTERFACE REQUIREMENT(S) MET: --
+ SI.1 THIS FILE

-- LOGICAL DATABASE REQUIREMENT(S) MET: --
+ LDR 1 CREATE TABLE FUNCTIONS
+ LDR 2 CREATE TABLE FUNCTIONS


--------------------------------------

The MIT License (MIT)

Copyright (c) 2021 OpenFin

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
**/

-- SI.1 SoCeRC++ shall have a database to store its files.
CREATE SCHEMA `finalproject` DEFAULT CHARACTER SET utf8;

drop table if exists `keywords`;
drop table if exists `functions`;
-- F.R. 3 Maintaining a database of C++ functions
CREATE TABLE functions (
    -- LDR 1 SoCeR shall assign all functions a unique id
    -- LDR 2 SoCeR storage system shall assign up to 32,767 valid ID numbers
    function_id INTEGER AUTO_INCREMENT PRIMARY KEY
    ,function_name VARCHAR(64)  NOT NULL
    ,function_content text NOT NULL
    ,total_keyword_weight VARCHAR(128) NOT NULL
    ,function_description text NOT NULL
    ,file_name VARCHAR(128)  NOT NULL
    ,file_content text NOT NULL
);

CREATE TABLE keywords (
    keyword_id INTEGER AUTO_INCREMENT PRIMARY KEY
    ,function_id INTEGER  NOT NULL
    ,function_name VARCHAR(64)  NOT NULL
    ,score varchar(128)  NOT NULL
    ,keyword VARCHAR(128)  NOT NULL
    ,CONSTRAINT fk_event_id FOREIGN KEY (function_id)
        REFERENCES functions(function_id)
);



select * from functions;
select * from keywords;