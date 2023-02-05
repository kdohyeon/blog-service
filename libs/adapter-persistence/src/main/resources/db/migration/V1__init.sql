DROP TABLE IF EXISTS blog_statistic;
CREATE TABLE blog_statistic
(
    id          BIGINT  NOT NULL AUTO_INCREMENT,
    keyword     VARCHAR(500) DEFAULT NULL,
    count       BIGINT          NOT NULL,
    created_at  TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    created_by  VARCHAR(50)  DEFAULT NULL,
    modified_at TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    modified_by VARCHAR(50)  DEFAULT NULL,
    PRIMARY KEY (id)
);
