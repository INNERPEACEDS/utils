DROP TABLE IF EXISTS `sys_user`;
DROP TABLE IF EXISTS `sys_role`;
DROP TABLE IF EXISTS `sys_menu`;
DROP TABLE IF EXISTS `sys_role_menu`;
DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE "WGB"."SYS_USER_ROLE"
(	"USER_ID" VARCHAR2(20 BYTE) NOT NULL ENABLE,
     "ROLE_ID" VARCHAR2(20 BYTE) NOT NULL ENABLE
) SEGMENT CREATION DEFERRED
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;


COMMENT ON COLUMN "WGB"."SYS_USER_ROLE"."USER_ID" IS '用户ID';

COMMENT ON COLUMN "WGB"."SYS_USER_ROLE"."ROLE_ID" IS '角色ID';



CREATE TABLE "WGB"."SYS_USER"
(	"USER_ID" VARCHAR2(2 BYTE) NOT NULL ENABLE,
     "USERNAME" VARCHAR2(50 BYTE) NOT NULL ENABLE,
     "PASSWORD" VARCHAR2(128 BYTE) NOT NULL ENABLE,
     "EMAIL" VARCHAR2(128 BYTE) DEFAULT NULL,
     "MOBILE" VARCHAR2(20 BYTE) DEFAULT NULL,
     "STATUS" VARCHAR2(1 BYTE) NOT NULL ENABLE,
     "LAST_LOGIN_TIME" TIMESTAMP (6) DEFAULT NULL,
     "CRATE_TIME" TIMESTAMP (6) NOT NULL ENABLE,
     "MODIFY_TIME" TIMESTAMP (6) DEFAULT NULL,
     PRIMARY KEY ("USER_ID")
         USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 NOCOMPRESS LOGGING
         TABLESPACE "USERS"  ENABLE
) SEGMENT CREATION DEFERRED
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;


COMMENT ON COLUMN "WGB"."SYS_USER"."USER_ID" IS '用户ID';

COMMENT ON COLUMN "WGB"."SYS_USER"."USERNAME" IS '用户名';

COMMENT ON COLUMN "WGB"."SYS_USER"."PASSWORD" IS '密码';

COMMENT ON COLUMN "WGB"."SYS_USER"."EMAIL" IS '邮箱';

COMMENT ON COLUMN "WGB"."SYS_USER"."MOBILE" IS '联系电话';

COMMENT ON COLUMN "WGB"."SYS_USER"."STATUS" IS '状态 0锁定 1有效';

COMMENT ON COLUMN "WGB"."SYS_USER"."LAST_LOGIN_TIME" IS '最近访问时间';

COMMENT ON COLUMN "WGB"."SYS_USER"."CRATE_TIME" IS '创建时间';

COMMENT ON COLUMN "WGB"."SYS_USER"."MODIFY_TIME" IS '修改时间';




CREATE TABLE "WGB"."SYS_ROLE_MENU"
(	"ROLE_ID" VARCHAR2(20 BYTE) NOT NULL ENABLE,
     "MENU_ID" VARCHAR2(20 BYTE) NOT NULL ENABLE
) SEGMENT CREATION DEFERRED
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;


COMMENT ON COLUMN "WGB"."SYS_ROLE_MENU"."ROLE_ID" IS '角色ID';

COMMENT ON COLUMN "WGB"."SYS_ROLE_MENU"."MENU_ID" IS '菜单/按钮ID';





CREATE TABLE "WGB"."SYS_ROLE"
(	"ROLE_ID" VARCHAR2(20 BYTE) NOT NULL ENABLE,
     "ROLE_NAME" VARCHAR2(100 BYTE) NOT NULL ENABLE,
     "REMARK" VARCHAR2(100 BYTE) DEFAULT NULL,
     "CREATE_TIME" TIMESTAMP (6) NOT NULL ENABLE,
     "MODIFY_TIME" TIMESTAMP (6) DEFAULT NULL,
     PRIMARY KEY ("ROLE_ID")
         USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 NOCOMPRESS LOGGING
         TABLESPACE "USERS"  ENABLE
) SEGMENT CREATION DEFERRED
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  TABLESPACE "USERS" ;


COMMENT ON COLUMN "WGB"."SYS_ROLE"."ROLE_ID" IS '角色ID';

COMMENT ON COLUMN "WGB"."SYS_ROLE"."ROLE_NAME" IS '角色名称';

COMMENT ON COLUMN "WGB"."SYS_ROLE"."REMARK" IS '角色描述';

COMMENT ON COLUMN "WGB"."SYS_ROLE"."CREATE_TIME" IS '创建时间';

COMMENT ON COLUMN "WGB"."SYS_ROLE"."MODIFY_TIME" IS '修改时间';





CREATE TABLE "WGB"."SYS_MENU"
(	"MENU_ID" VARCHAR2(20 BYTE) NOT NULL ENABLE,
     "PARENT_ID" VARCHAR2(20 BYTE) NOT NULL ENABLE,
     "MENU_NAME" VARCHAR2(50 BYTE) NOT NULL ENABLE,
     "URL" VARCHAR2(100 BYTE) DEFAULT NULL,
     "PERMS" VARCHAR2(256 BYTE),
     "ICON" VARCHAR2(50 BYTE) DEFAULT NULL,
     "TYPE" VARCHAR2(1 BYTE) NOT NULL ENABLE,
     "ORDER_NUM" VARCHAR2(20 BYTE) DEFAULT NULL,
     "CREATE_TIME" TIMESTAMP (6) NOT NULL ENABLE,
     "MODIFY_TIME" TIMESTAMP (6) DEFAULT NULL
) SEGMENT CREATION IMMEDIATE
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;


COMMENT ON COLUMN "WGB"."SYS_MENU"."MENU_ID" IS '菜单/按钮ID';

COMMENT ON COLUMN "WGB"."SYS_MENU"."PARENT_ID" IS '上级菜单ID';

COMMENT ON COLUMN "WGB"."SYS_MENU"."MENU_NAME" IS '菜单/按钮名称';

COMMENT ON COLUMN "WGB"."SYS_MENU"."URL" IS '菜单URL';

COMMENT ON COLUMN "WGB"."SYS_MENU"."PERMS" IS '权限标识';

COMMENT ON COLUMN "WGB"."SYS_MENU"."ICON" IS '图标';

COMMENT ON COLUMN "WGB"."SYS_MENU"."TYPE" IS '类型 0菜单 1按钮';

COMMENT ON COLUMN "WGB"."SYS_MENU"."ORDER_NUM" IS '排序';

COMMENT ON COLUMN "WGB"."SYS_MENU"."CREATE_TIME" IS '创建时间';

COMMENT ON COLUMN "WGB"."SYS_MENU"."MODIFY_TIME" IS '修改时间';

