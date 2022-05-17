<<<<<<< Updated upstream


create table [dbo].[providers_audit](
	[id] [varchar](200) NOT NULL,
	[request_json] [nvarchar](max) NULL,
	[response_json] [nvarchar](max) NULL,
	[creation_at] [datetime] NULL,
	[last_maint_at] [datetime] NULL,
	PRIMARY KEY (id)
 );
=======
create table dbo.providers_audit
(
    id varchar(200) NOT NULL,
    request_json nvarchar(max) NULL,
    response_json nvarchar(max) NULL,
    creation_at datetime DEFAULT getdate() NULL,
    last_updated_at datetime DEFAULT getdate() NULL,
    CONSTRAINT pk_providers_audit_id PRIMARY KEY (id)
);
>>>>>>> Stashed changes
