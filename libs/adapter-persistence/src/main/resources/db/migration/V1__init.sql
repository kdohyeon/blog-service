DROP TABLE IF EXISTS blog_statistic;
CREATE TABLE blog_statistic
(
    id          BIGINT       NOT NULL AUTO_INCREMENT,
    keyword     VARCHAR(500) NOT NULL,
    count       BIGINT       NOT NULL,
    created_at  TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    created_by  VARCHAR(50)  DEFAULT NULL,
    modified_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    modified_by VARCHAR(50)  DEFAULT NULL,
    PRIMARY KEY (id)
);

INSERT INTO blog_statistic (id, keyword, count, created_at, created_by, modified_at, modified_by)
values (10, '자바', 4232, '2023-02-07 05:23:32.627696', 'SYSTEM', '2023-2-07 05:30:34.767035', 'SYSTEM');

INSERT INTO blog_statistic (id, keyword, count, created_at, created_by, modified_at, modified_by)
values (11, '스프링', 3939, '2023-02-07 05:23:32.627696', 'SYSTEM', '2023-2-07 05:30:34.767035', 'SYSTEM');

INSERT INTO blog_statistic (id, keyword, count, created_at, created_by, modified_at, modified_by)
values (12, '코틀린', 40104, '2023-02-07 05:23:32.627696', 'SYSTEM', '2023-2-07 05:30:34.767035', 'SYSTEM');

INSERT INTO blog_statistic (id, keyword, count, created_at, created_by, modified_at, modified_by)
values (13, 'JPA', 5783, '2023-02-07 05:23:32.627696', 'SYSTEM', '2023-2-07 05:30:34.767035', 'SYSTEM');

INSERT INTO blog_statistic (id, keyword, count, created_at, created_by, modified_at, modified_by)
values (14, '테스트환경', 49237, '2023-02-07 05:23:32.627696', 'SYSTEM', '2023-2-07 05:30:34.767035', 'SYSTEM');
