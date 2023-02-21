create table companies
(
    id  bigint auto_increment
        primary key,
    name varchar(255) null
);

create table stocks
(
    id bigint auto_increment primary key,
    stock_change double       null,
    company_name varchar(255) null,
    currency     varchar(255) null,
    price        double       null,
    symbol       varchar(255) null,
    volume       bigint       null
);

create table stocks_history
(
    id            bigint                              not null,
    symbol        varchar(255)                        not null,
    company_name  varchar(255)                        not null,
    currency      varchar(45)                         not null,
    price         float                               not null,
    stock_change  float                               not null,
    volume        bigint                              null,
    last_modified timestamp default CURRENT_TIMESTAMP not null
);

DROP TRIGGER IF
    EXISTS stocks_AFTER_UPDATE;
CREATE TRIGGER stocks_AFTER_UPDATE
    AFTER UPDATE
    ON stocks
    FOR EACH ROW
    INSERT INTO stocks_history
    VALUES (OLD.id,
            OLD.symbol,
            OLD.company_name,
            OLD.currency,
            OLD.price,
            (NEW.stock_change - OLD.stock_change),
            OLD.volume,
            NOW());