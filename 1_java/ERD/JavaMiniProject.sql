CREATE TABLE `사용자` (
	`번호`	INT(11)	NOT NULL,
	`사용자 아이디`	VARCHAR(20)	NULL,
	`사용자 이름`	VARCHAR(20)	NULL,
	`비밀번호`	VARCHAR(80)	NULL,
	`나이`	INT(11)	NULL,
	`성별`	VARCHAR(1)	NULL,
	`전화번호`	VARCHAR(20)	NULL,
	`이메일`	VARCHAR(20)	NULL,
	`사용자 유형`	VARCHAR(20)	NULL,
	`사용자 번호`	INT(11)	NOT NULL,
	`강의실 번호`	INT(11)	NOT NULL
);

CREATE TABLE `회원가입 요청` (
	`번호`	INT(11)	NOT NULL,
	`사용자 아이디`	VARCHAR(20)	NULL,
	`사용자 이름`	VARCHAR(20)	NULL,
	`비밀번호`	VARCHAR(80)	NULL,
	`나이`	INT(11)	NULL,
	`성별`	VARCHAR(1)	NULL,
	`전화번호`	VARCHAR(20)	NULL,
	`이메일`	VARCHAR(20)	NULL,
	`사용자 유형`	VARCHAR(20)	NULL
);

CREATE TABLE `강의실` (
	`번호`	INT(11)	NOT NULL,
	`주소`	VARCHAR(20)	NULL,
	`이름`	VARCHAR(20)	NULL,
	`층`	INT(11)	NULL,
	`반이름`	VARCHAR(1)	NULL
);

CREATE TABLE `자습대장` (
	`번호`	INT(11)	NOT NULL,
	`날짜`	DATE	NULL,
	`시간`	TIME	NULL,
	`사용자 번호`	INT(11)	NOT NULL
);

ALTER TABLE `사용자` ADD CONSTRAINT `PK_사용자` PRIMARY KEY (
	`번호`
);

ALTER TABLE `회원가입 요청` ADD CONSTRAINT `PK_회원가입 요청` PRIMARY KEY (
	`번호`
);

ALTER TABLE `강의실` ADD CONSTRAINT `PK_강의실` PRIMARY KEY (
	`번호`
);

ALTER TABLE `자습대장` ADD CONSTRAINT `PK_자습대장` PRIMARY KEY (
	`번호`
);

