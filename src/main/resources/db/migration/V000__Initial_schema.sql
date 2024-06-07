create table sentemail
(
	id bigserial not null,
	data varchar(4000) not null,
    emailType varchar(255) not null,
	fromEmail varchar(255) not null,
	fromName varchar(255) not null,
	toEmail varchar(255) not null,
	toName varchar(255) not null,
    preferredLocale varchar(255),
    created timestamp not null,
    updated timestamp not null ,
    deleted timestamp,
    sent timestamp,
    primary key (id)
);


CREATE INDEX idx_sentemail_toemail ON sentemail (toemail);
CREATE INDEX idx_sentemail_fromemail ON sentemail (fromemail);
CREATE INDEX idx_sentemail_sent ON sentemail (sent);
