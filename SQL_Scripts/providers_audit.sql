

create table [dbo].[providers_audit](
	[id] [varchar](200) NOT NULL,
	[request_json] [nvarchar](max) NULL,
	[response_json] [nvarchar](max) NULL,
	[creation_at] [datetime] NULL,
	[last_maint_at] [datetime] NULL,
	PRIMARY KEY (id)
 );