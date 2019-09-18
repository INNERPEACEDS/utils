DELETE FROM sys_user;
DELETE FROM sys_role;
DELETE FROM sys_menu;
DELETE FROM sys_role_menu;
DELETE FROM sys_user_role;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO sys_menu VALUES ('1', '0', '系统管理', null, null, 'zmdi zmdi-settings', '0', '1', SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('2', '0', '系统监控', null, null, 'zmdi zmdi-shield-security', '0', '2', SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('3', '1', '用户管理', 'user', 'user:list', '', '0', '1', SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('4', '1', '角色管理', 'role', 'role:list', '', '0', '2', SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('5', '1', '菜单管理', 'menu', 'menu:list', '', '0', '3', SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('6', '1', '部门管理', 'dept', 'dept:list', '', '0', '4', SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('8', '2', '在线用户', 'session', 'session:list', '', '0', '1', SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('10', '2', '系统日志', 'log', 'log:list', '', '0', '3', SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('11', '3', '新增用户', null, 'user:add', null, '1', null, SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('12', '3', '修改用户', null, 'user:update', null, '1', null, SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('13', '3', '删除用户', null, 'user:delete', null, '1', null, SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('14', '4', '新增角色', null, 'role:add', null, '1', null, SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('15', '4', '修改角色', null, 'role:update', null, '1', null, SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('16', '4', '删除角色', null, 'role:delete', null, '1', null, SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('17', '5', '新增菜单', null, 'menu:add', null, '1', null, SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('18', '5', '修改菜单', null, 'menu:update', null, '1', null, SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('19', '5', '删除菜单', null, 'menu:delete', null, '1', null, SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('20', '6', '新增部门', null, 'dept:add', null, '1', null, SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('21', '6', '修改部门', null, 'dept:update', null, '1', null, SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('22', '6', '删除部门', null, 'dept:delete', null, '1', null, SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('23', '8', '踢出用户', null, 'user:kickout', null, '1', null, SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('24', '10', '删除日志', null, 'log:delete', null, '1', null, SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('58', '0', '网络资源', null, null, 'zmdi zmdi-globe-alt', '0', null, SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('59', '58', '天气查询', 'weather', 'weather:list', '', '0', null, SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('61', '58', '每日一文', 'article', 'article:list', '', '0', null, SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('64', '1', '字典管理', 'dict', 'dict:list', '', '0', null, SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('65', '64', '新增字典', null, 'dict:add', null, '1', null, SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('66', '64', '修改字典', null, 'dict:update', null, '1', null, SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('67', '64', '删除字典', null, 'dict:delete', null, '1', null, SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('81', '58', '影视资讯', null, null, null, '0', null, SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('82', '81', '正在热映', 'movie/hot', 'movie:hot', '', '0', null, SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('83', '81', '即将上映', 'movie/coming', 'movie:coming', '', '0', null, SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('86', '58', 'One 一个', null, null, null, '0', null, SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('87', '86', '绘画', 'one/painting', 'one:painting', '', '0', null, SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('88', '86', '语文', 'one/yuwen', 'one:yuwen', '', '0', null, SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('89', '86', '散文', 'one/essay', 'one:essay', '', '0', null, SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('101', '0', '任务调度', null, null, 'zmdi zmdi-alarm', '0', null, SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('102', '101', '定时任务', 'job', 'job:list', '', '0', null, SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('103', '102', '新增任务', null, 'job:add', null, '1', null, SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('104', '102', '修改任务', null, 'job:update', null, '1', null, SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('105', '102', '删除任务', null, 'job:delete', null, '1', null, SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('106', '102', '暂停任务', null, 'job:pause', null, '1', null, SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('107', '102', '恢复任务', null, 'job:resume', null, '1', null, SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('108', '102', '立即执行任务', null, 'job:run', null, '1', null, SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('109', '101', '调度日志', 'jobLog', 'jobLog:list', '', '0', null, SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('110', '109', '删除日志', null, 'jobLog:delete', null, '1', null, SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('113', '2', 'Redis监控', 'redis/info', 'redis:list', '', '0', null, SYSTIMESTAMP, null);
INSERT INTO sys_menu VALUES ('114', '2', 'Redis终端', 'redis/terminal', 'redis:terminal', '', '0', null, SYSTIMESTAMP, null);



-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO sys_role VALUES ('1', '管理员', '管理员', SYSTIMESTAMP, SYSTIMESTAMP);
INSERT INTO sys_role VALUES ('2', '测试账号', '测试账号', SYSTIMESTAMP, SYSTIMESTAMP);
INSERT INTO sys_role VALUES ('3', '注册账户', '注册账户，只可查看，不可操作', SYSTIMESTAMP, SYSTIMESTAMP);
INSERT INTO sys_role VALUES ('23', '用户管理员', '负责用户的增删改操作', SYSTIMESTAMP, null);
INSERT INTO sys_role VALUES ('24', '系统监控员', '可查看系统监控信息，但不可操作', SYSTIMESTAMP, SYSTIMESTAMP);
INSERT INTO sys_role VALUES ('25', '用户查看', '查看用户，无相应操作权限', SYSTIMESTAMP, null);
INSERT INTO sys_role VALUES ('63', '影院工作者', '可查看影视信息', SYSTIMESTAMP, SYSTIMESTAMP);
INSERT INTO sys_role VALUES ('64', '天气预报员', '可查看天气预报信息', SYSTIMESTAMP, null);
INSERT INTO sys_role VALUES ('65', '文章审核', '文章类', SYSTIMESTAMP, SYSTIMESTAMP);



-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO sys_role_menu VALUES ('3', '58');
INSERT INTO sys_role_menu VALUES ('3', '59');
INSERT INTO sys_role_menu VALUES ('3', '61');
INSERT INTO sys_role_menu VALUES ('3', '81');
INSERT INTO sys_role_menu VALUES ('3', '82');
INSERT INTO sys_role_menu VALUES ('3', '83');
INSERT INTO sys_role_menu VALUES ('3', '86');
INSERT INTO sys_role_menu VALUES ('3', '87');
INSERT INTO sys_role_menu VALUES ('3', '88');
INSERT INTO sys_role_menu VALUES ('3', '89');
INSERT INTO sys_role_menu VALUES ('3', '1');
INSERT INTO sys_role_menu VALUES ('3', '3');
INSERT INTO sys_role_menu VALUES ('3', '4');
INSERT INTO sys_role_menu VALUES ('3', '5');
INSERT INTO sys_role_menu VALUES ('3', '6');
INSERT INTO sys_role_menu VALUES ('3', '64');
INSERT INTO sys_role_menu VALUES ('3', '2');
INSERT INTO sys_role_menu VALUES ('3', '8');
INSERT INTO sys_role_menu VALUES ('3', '10');
INSERT INTO sys_role_menu VALUES ('3', '101');
INSERT INTO sys_role_menu VALUES ('3', '102');
INSERT INTO sys_role_menu VALUES ('3', '109');
INSERT INTO sys_role_menu VALUES ('63', '58');
INSERT INTO sys_role_menu VALUES ('63', '81');
INSERT INTO sys_role_menu VALUES ('63', '82');
INSERT INTO sys_role_menu VALUES ('63', '83');
INSERT INTO sys_role_menu VALUES ('24', '8');
INSERT INTO sys_role_menu VALUES ('24', '2');
INSERT INTO sys_role_menu VALUES ('24', '10');
INSERT INTO sys_role_menu VALUES ('65', '86');
INSERT INTO sys_role_menu VALUES ('65', '88');
INSERT INTO sys_role_menu VALUES ('65', '89');
INSERT INTO sys_role_menu VALUES ('65', '58');
INSERT INTO sys_role_menu VALUES ('65', '61');
INSERT INTO sys_role_menu VALUES ('2', '81');
INSERT INTO sys_role_menu VALUES ('2', '61');
INSERT INTO sys_role_menu VALUES ('2', '24');
INSERT INTO sys_role_menu VALUES ('2', '82');
INSERT INTO sys_role_menu VALUES ('2', '83');
INSERT INTO sys_role_menu VALUES ('2', '58');
INSERT INTO sys_role_menu VALUES ('2', '59');
INSERT INTO sys_role_menu VALUES ('2', '2');
INSERT INTO sys_role_menu VALUES ('2', '8');
INSERT INTO sys_role_menu VALUES ('2', '10');
INSERT INTO sys_role_menu VALUES ('23', '11');
INSERT INTO sys_role_menu VALUES ('23', '12');
INSERT INTO sys_role_menu VALUES ('23', '13');
INSERT INTO sys_role_menu VALUES ('23', '3');
INSERT INTO sys_role_menu VALUES ('23', '1');
INSERT INTO sys_role_menu VALUES ('25', '1');
INSERT INTO sys_role_menu VALUES ('25', '3');
INSERT INTO sys_role_menu VALUES ('1', '59');
INSERT INTO sys_role_menu VALUES ('1', '2');
INSERT INTO sys_role_menu VALUES ('1', '3');
INSERT INTO sys_role_menu VALUES ('1', '67');
INSERT INTO sys_role_menu VALUES ('1', '1');
INSERT INTO sys_role_menu VALUES ('1', '4');
INSERT INTO sys_role_menu VALUES ('1', '5');
INSERT INTO sys_role_menu VALUES ('1', '6');
INSERT INTO sys_role_menu VALUES ('1', '20');
INSERT INTO sys_role_menu VALUES ('1', '21');
INSERT INTO sys_role_menu VALUES ('1', '22');
INSERT INTO sys_role_menu VALUES ('1', '10');
INSERT INTO sys_role_menu VALUES ('1', '8');
INSERT INTO sys_role_menu VALUES ('1', '58');
INSERT INTO sys_role_menu VALUES ('1', '66');
INSERT INTO sys_role_menu VALUES ('1', '11');
INSERT INTO sys_role_menu VALUES ('1', '12');
INSERT INTO sys_role_menu VALUES ('1', '64');
INSERT INTO sys_role_menu VALUES ('1', '13');
INSERT INTO sys_role_menu VALUES ('1', '14');
INSERT INTO sys_role_menu VALUES ('1', '65');
INSERT INTO sys_role_menu VALUES ('1', '15');
INSERT INTO sys_role_menu VALUES ('1', '16');
INSERT INTO sys_role_menu VALUES ('1', '17');
INSERT INTO sys_role_menu VALUES ('1', '18');
INSERT INTO sys_role_menu VALUES ('1', '23');
INSERT INTO sys_role_menu VALUES ('1', '81');
INSERT INTO sys_role_menu VALUES ('1', '82');
INSERT INTO sys_role_menu VALUES ('1', '83');
INSERT INTO sys_role_menu VALUES ('1', '19');
INSERT INTO sys_role_menu VALUES ('1', '24');
INSERT INTO sys_role_menu VALUES ('1', '61');
INSERT INTO sys_role_menu VALUES ('1', '86');
INSERT INTO sys_role_menu VALUES ('1', '87');
INSERT INTO sys_role_menu VALUES ('1', '88');
INSERT INTO sys_role_menu VALUES ('1', '89');
INSERT INTO sys_role_menu VALUES ('1', '101');
INSERT INTO sys_role_menu VALUES ('1', '102');
INSERT INTO sys_role_menu VALUES ('1', '103');
INSERT INTO sys_role_menu VALUES ('1', '104');
INSERT INTO sys_role_menu VALUES ('1', '105');
INSERT INTO sys_role_menu VALUES ('1', '106');
INSERT INTO sys_role_menu VALUES ('1', '107');
INSERT INTO sys_role_menu VALUES ('1', '108');
INSERT INTO sys_role_menu VALUES ('1', '109');
INSERT INTO sys_role_menu VALUES ('1', '110');
INSERT INTO sys_role_menu VALUES ('64', '59');
INSERT INTO sys_role_menu VALUES ('64', '58');
INSERT INTO sys_role_menu VALUES ('1', '113');
INSERT INTO sys_role_menu VALUES ('1', '114');



-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO sys_user VALUES ('4', 'MrBird', '42ee25d1e43e9f57119a00d0a39e5250', 'mrbird@hotmail.com', '13455533222', '1', SYSTIMESTAMP, SYSTIMESTAMP, SYSTIMESTAMP);
INSERT INTO sys_user VALUES ('6', 'tester', '243e29429b340192700677d48c09d992',  'tester@qq.com', '13888888888', '1', SYSTIMESTAMP, SYSTIMESTAMP, SYSTIMESTAMP);
INSERT INTO sys_user VALUES ('23', 'scott', 'ac3af72d9f95161a502fd326865c2f15', 'scott@qq.com', '15134627380', '1', SYSTIMESTAMP, SYSTIMESTAMP, SYSTIMESTAMP);
INSERT INTO sys_user VALUES ('24', 'smith', '228208eafc74e48c44619cc543fc0efe', 'smith@qq.com', '13364754932', '1', SYSTIMESTAMP, SYSTIMESTAMP, SYSTIMESTAMP);
INSERT INTO sys_user VALUES ('25', 'allen', '83baac97928a113986054efacaeec1d2',  'allen@qq.com', '13427374857', '0', SYSTIMESTAMP, SYSTIMESTAMP, SYSTIMESTAMP);
INSERT INTO sys_user VALUES ('26', 'martin', 'b26c9edca9a61016bca1f6fb042e679e', 'martin@qq.com', '15562736678', '1', SYSTIMESTAMP, SYSTIMESTAMP, SYSTIMESTAMP);
INSERT INTO sys_user VALUES ('27', 'ford', '0448f0dcfd856b0e831842072b532141',  'ford@qq.com', '15599998373', '0', SYSTIMESTAMP, SYSTIMESTAMP, SYSTIMESTAMP);
INSERT INTO sys_user VALUES ('91', '系统监控员', '7c28d1cd33414ac15832f7be92668b7a', 'xtjk@qq.com', '18088736652', '1', SYSTIMESTAMP, SYSTIMESTAMP, SYSTIMESTAMP);

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO sys_user_role VALUES ('27', '3');
INSERT INTO sys_user_role VALUES ('24', '65');
INSERT INTO sys_user_role VALUES ('26', '3');
INSERT INTO sys_user_role VALUES ('26', '23');
INSERT INTO sys_user_role VALUES ('26', '24');
INSERT INTO sys_user_role VALUES ('25', '3');
INSERT INTO sys_user_role VALUES ('91', '24');
INSERT INTO sys_user_role VALUES ('4', '1');
INSERT INTO sys_user_role VALUES ('6', '1');
INSERT INTO sys_user_role VALUES ('6', '2');
INSERT INTO sys_user_role VALUES ('6', '3');
INSERT INTO sys_user_role VALUES ('6', '25');
INSERT INTO sys_user_role VALUES ('6', '63');
INSERT INTO sys_user_role VALUES ('23', '2');
INSERT INTO sys_user_role VALUES ('23', '3');
INSERT INTO sys_user_role VALUES ('23', '23');
INSERT INTO sys_user_role VALUES ('23', '24');
INSERT INTO sys_user_role VALUES ('23', '25');