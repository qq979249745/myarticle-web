
create table if not exists user (userName varchar(32) primary key,password varchar(32),userImg blob);
create table if not exists article  (articleid char(16) primary key auto_increment,title varchar(128),content text,submitTime bigint,watchNum int,praiseNum int,commentNum int ,userName varchar(32) ,foreign key(userName) references user(userName));
create table if not exists  comment (commentid char(16) primary key auto_increment,articleid char(16),userName varchar(32),comment_content varchar(255),comment_time bigint,foreign key(articleid) references article(articleid),foreign key(userName) references user(userName));
