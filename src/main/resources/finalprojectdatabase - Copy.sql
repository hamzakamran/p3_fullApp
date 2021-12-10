CREATE SCHEMA `finalproject` DEFAULT CHARACTER SET utf8;

drop table if exists `keywords`;
drop table if exists `functions`;
CREATE TABLE functions (
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