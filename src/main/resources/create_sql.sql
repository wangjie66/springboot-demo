CREATE TABLE app_app_version
(
  app_id        INT(11) UNSIGNED NOT NULL
  COMMENT '应用id',
  appversion_id INT(11) UNSIGNED NOT NULL
  COMMENT '应用版本id',
  CONSTRAINT app_appversion_uniqueId
  UNIQUE (appversion_id, app_id)
)
  ENGINE = InnoDB;

CREATE TABLE app_cve
(
  appversion_id INT(11) UNSIGNED                    NOT NULL
  COMMENT '应用版本Id',
  cve_number    VARCHAR(64)                         NOT NULL
  COMMENT 'CVE编号',
  hash          VARCHAR(256)                        NOT NULL
  COMMENT 'hash',
  score         VARCHAR(16) DEFAULT 'unknow'        NOT NULL
  COMMENT 'CVE分数',
  status        TINYINT DEFAULT '0'                 NOT NULL
  COMMENT '状态是否被解决',
  create_time   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  COMMENT '创建时间',
  modify_time   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP
  COMMENT '修改时间',
  CONSTRAINT app_cves_uniqueId
  UNIQUE (appversion_id, cve_number)
)
  ENGINE = InnoDB;

CREATE TABLE app_server_container
(
  id           BIGINT UNSIGNED AUTO_INCREMENT
    PRIMARY KEY,
  app_id       INT NOT NULL,
  server_id    INT NOT NULL,
  container_id INT NOT NULL,
  CONSTRAINT uk_app_server_container
  UNIQUE (server_id, container_id, app_id)
)
  ENGINE = InnoDB;

CREATE TABLE app_version
(
  id               INT(11) UNSIGNED AUTO_INCREMENT
  COMMENT '主键'
    PRIMARY KEY,
  app_version_uuid VARCHAR(36)                             NOT NULL
  COMMENT '应用版本编号',
  name             VARCHAR(256) DEFAULT 'Defalult Version' NOT NULL
  COMMENT '版本名称',
  lines_of_code    INT(11) UNSIGNED                        NULL
  COMMENT '代码总行数',
  default_version  TINYINT DEFAULT '0'                     NOT NULL
  COMMENT 'is-default-version:1;',
  create_time      TIMESTAMP DEFAULT CURRENT_TIMESTAMP     NOT NULL
  COMMENT '创建时间',
  modify_time      TIMESTAMP DEFAULT CURRENT_TIMESTAMP     NOT NULL ON UPDATE CURRENT_TIMESTAMP
  COMMENT '修改时间',
  CONSTRAINT application_uniqueId
  UNIQUE (app_version_uuid)
)
  ENGINE = InnoDB;

CREATE TABLE app_version_jar
(
  id                  BIGINT UNSIGNED AUTO_INCREMENT
    PRIMARY KEY,
  app_version_id      INT(11) UNSIGNED                    NOT NULL
  COMMENT '应用版本ID',
  file_name           VARCHAR(128)                        NOT NULL
  COMMENT '文件名称',
  hash                VARCHAR(256)                        NOT NULL
  COMMENT 'hash',
  exist_in_lib        TINYINT DEFAULT '0'                 NOT NULL
  COMMENT '1:exist;default:0;',
  path                VARCHAR(256) DEFAULT 'unknow'       NOT NULL
  COMMENT '此包所在web容器的路径',
  class_count         INT DEFAULT '0'                     NOT NULL
  COMMENT '第三方库的类统计',
  used_class          INT DEFAULT '0'                     NOT NULL
  COMMENT '使用的类数',
  total_lines_of_code INT DEFAULT '0'                     NOT NULL
  COMMENT '第三方库代码行数',
  grade               VARCHAR(8) DEFAULT 'N/A'            NOT NULL
  COMMENT '第三方库等级',
  create_time         TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  COMMENT '创建时间',
  modify_time         TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP
  COMMENT '修改时间',
  cve_num             INT                                 NULL
  COMMENT '统计cve数量',
  CONSTRAINT app_jars_uniqueId
  UNIQUE (app_version_id, hash)
)
  ENGINE = InnoDB;

CREATE TABLE application
(
  id          INT(11) UNSIGNED AUTO_INCREMENT
  COMMENT '主键'
    PRIMARY KEY,
  app_uuid    VARCHAR(36)                         NOT NULL
  COMMENT '应用版本编号',
  language    VARCHAR(128) DEFAULT 'JAVA'         NOT NULL
  COMMENT '语言',
  name        VARCHAR(256) DEFAULT 'ROOT'         NOT NULL
  COMMENT '检测到的应用名称',
  show_name   VARCHAR(256) DEFAULT 'ROOT'         NOT NULL
  COMMENT '显示的应用名称',
  importance  TINYINT DEFAULT '0'                 NULL
  COMMENT '重要性',
  path        VARCHAR(256) DEFAULT '/'            NULL
  COMMENT '应用路径',
  create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  COMMENT '创建时间',
  modify_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP
  COMMENT '修改时间',
  CONSTRAINT application_uniqueId
  UNIQUE (app_uuid)
)
  ENGINE = InnoDB;

CREATE TABLE baseline
(
  id              BIGINT UNSIGNED AUTO_INCREMENT
    PRIMARY KEY,
  name            VARCHAR(255)                        NOT NULL,
  baseline_number VARCHAR(50)                         NOT NULL,
  description     VARCHAR(1024)                       NOT NULL,
  check_process   VARCHAR(255)                        NOT NULL,
  judgment_basis  VARCHAR(500)                        NOT NULL,
  note            VARCHAR(255)                        NULL,
  modify_time     TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  create_time     TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
)
  ENGINE = InnoDB;

CREATE TABLE baseline_evidence
(
  id              BIGINT UNSIGNED AUTO_INCREMENT
    PRIMARY KEY,
  evidence_detail VARCHAR(255)           NOT NULL,
  pass            TINYINT(1) DEFAULT '0' NOT NULL
  COMMENT '0:false'
)
  ENGINE = InnoDB;

CREATE TABLE baseline_evidence_container
(
  id                           BIGINT UNSIGNED AUTO_INCREMENT
    PRIMARY KEY,
  evidence_id                  INT NOT NULL,
  container_server_baseline_id INT NOT NULL,
  CONSTRAINT uk_baseline_evidence_contaier
  UNIQUE (container_server_baseline_id, evidence_id)
)
  ENGINE = InnoDB;

CREATE TABLE cnnvd_table
(
  name           VARCHAR(256)  NULL,
  cnnvd_id       VARCHAR(50)   NOT NULL
    PRIMARY KEY,
  published_date VARCHAR(50)   NULL,
  modified_date  VARCHAR(50)   NULL,
  source         VARCHAR(1024) NULL,
  severity       VARCHAR(50)   NULL,
  vuln_type      VARCHAR(50)   NULL,
  thrtype        VARCHAR(50)   NULL,
  vuln_descript  BLOB          NULL,
  cve_id         VARCHAR(50)   NULL,
  bugtraq_id     VARCHAR(500)  NULL,
  vuln_solution  BLOB          NULL,
  CONSTRAINT cnnvd_id_uk
  UNIQUE (cnnvd_id)
)
  ENGINE = InnoDB;

CREATE INDEX cve_id_idx
  ON cnnvd_table (cve_id);

CREATE TABLE container
(
  id             BIGINT UNSIGNED AUTO_INCREMENT
    PRIMARY KEY,
  container_uuid VARCHAR(36)                         NOT NULL,
  name           VARCHAR(255)                        NOT NULL,
  version        VARCHAR(255)                        NOT NULL,
  jdk            VARCHAR(255)                        NULL,
  release_date   VARCHAR(50)                         NULL,
  source         TINYINT DEFAULT '0'                 NOT NULL
  COMMENT '0:sys;1:custom',
  grade          VARCHAR(4)                          NULL,
  cves           TINYINT DEFAULT '0'                 NOT NULL,
  cnnvds         TINYINT DEFAULT '0'                 NOT NULL,
  modify_time    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  create_time    TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
)
  ENGINE = InnoDB;

CREATE TABLE container_cve
(
  container_id BIGINT      NOT NULL,
  cve_number   VARCHAR(20) NOT NULL,
  CONSTRAINT uk_container_cve
  UNIQUE (container_id, cve_number)
)
  ENGINE = InnoDB;

CREATE TABLE container_server_baseline
(
  id           BIGINT UNSIGNED AUTO_INCREMENT
    PRIMARY KEY,
  server_id    INT                                 NOT NULL,
  container_id INT                                 NOT NULL,
  baseline_id  INT                                 NOT NULL,
  pass         TINYINT(1) DEFAULT '0'              NOT NULL
  COMMENT '0:false',
  modify_time  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  create_time  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  CONSTRAINT uk_container_server_baseline
  UNIQUE (server_id, container_id, baseline_id)
)
  ENGINE = InnoDB;

CREATE TABLE cpe_cve_table
(
  id          INT AUTO_INCREMENT
    PRIMARY KEY,
  hash        VARCHAR(255)                        NULL,
  jar_name    VARCHAR(255)                        NULL,
  cve_number  VARCHAR(255)                        NOT NULL,
  modify_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT cve_cpe_uk1
  UNIQUE (jar_name, cve_number)
)
  ENGINE = InnoDB
  CHARSET = utf8;

CREATE TABLE cve_table
(
  id                     INT AUTO_INCREMENT
    PRIMARY KEY,
  availability_impact    VARCHAR(255)                        NULL,
  name                   VARCHAR(255)                        NOT NULL,
  description            VARCHAR(4000)                       NULL,
  status                 VARCHAR(255)                        NULL,
  access_vector          VARCHAR(255)                        NULL,
  access_complexity      VARCHAR(255) DEFAULT 'MEDIUM'       NULL,
  authentication         VARCHAR(255)                        NULL,
  confidentiality_impact VARCHAR(255)                        NULL,
  integrity_impact       VARCHAR(255)                        NULL,
  score                  VARCHAR(255)                        NULL,
  cwe                    VARCHAR(4000)                       NULL,
  modify_time            TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT cves_uk_1
  UNIQUE (name)
)
  ENGINE = InnoDB
  CHARSET = utf8;

CREATE TABLE jar_info
(
  id                  INT AUTO_INCREMENT
    PRIMARY KEY,
  version             VARCHAR(255)                        NOT NULL,
  hash                VARCHAR(255)                        NOT NULL,
  group_name          VARCHAR(255)                        NULL,
  artifact_name       VARCHAR(255)                        NOT NULL,
  release_date        BIGINT                              NULL,
  latest_release_date BIGINT                              NULL,
  latest_version      VARCHAR(255)                        NULL,
  versions_behind     INT                                 NULL,
  date_profiled       TIMESTAMP DEFAULT CURRENT_TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
  grade               VARCHAR(4)                          NULL,
  CONSTRAINT java_artifacts_hash_uk
  UNIQUE (hash)
)
  ENGINE = InnoDB;

CREATE INDEX java_artifacts_version_idx
  ON jar_info (version);

CREATE INDEX group_artifact_name_idx
  ON jar_info (group_name, artifact_name);

CREATE INDEX java_artifacts_group_name_idx
  ON jar_info (group_name);

CREATE INDEX java_artifacts_artifact_name_idx
  ON jar_info (artifact_name);

CREATE TABLE sca_portal_version
(
  id                   INT                                 NOT NULL
    PRIMARY KEY,
  current_version      VARCHAR(64)                         NOT NULL,
  current_version_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  description          VARCHAR(255)                        NULL
)
  ENGINE = InnoDB;

CREATE TABLE server
(
  id          INT(11) UNSIGNED AUTO_INCREMENT
  COMMENT '主键'
    PRIMARY KEY,
  server_uuid VARCHAR(36)                         NOT NULL
  COMMENT '服务器编号',
  hostname    VARCHAR(128) DEFAULT 'localhost'    NOT NULL
  COMMENT '服务器主机名',
  ip          VARCHAR(36)                         NULL,
  status      TINYINT DEFAULT '0'                 NOT NULL
  COMMENT 'web容器状态',
  online_time INT DEFAULT '0'                     NOT NULL
  COMMENT 'web容器在线时间',
  create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
  COMMENT '创建时间',
  modify_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP
  COMMENT '修改时间',
  CONSTRAINT server_uniqueId
  UNIQUE (server_uuid)
)
  ENGINE = InnoDB;

CREATE TABLE server_container_config
(
  id             BIGINT UNSIGNED AUTO_INCREMENT
    PRIMARY KEY,
  server_id      INT          NOT NULL,
  container_id   INT          NOT NULL,
  engine_version VARCHAR(255) NULL,
  config         VARCHAR(255) NULL,
  CONSTRAINT uk_server_container
  UNIQUE (server_id, container_id)
)
  ENGINE = InnoDB;

