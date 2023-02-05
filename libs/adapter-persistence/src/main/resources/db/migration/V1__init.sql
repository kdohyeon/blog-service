DROP TABLE IF EXISTS blog_search_statistics;
CREATE TABLE blog_search_statistics
(
    id          VARCHAR(50)  NOT NULL,
    keyword     VARCHAR(500) DEFAULT NULL,
    uri         VARCHAR(500) NOT NULL,
    count       INT          NOT NULL,
    created_at  TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    created_by  VARCHAR(50)  DEFAULT NULL,
    modified_at  TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
    modified_by  VARCHAR(50)  DEFAULT NULL,
    PRIMARY KEY (id)
);
