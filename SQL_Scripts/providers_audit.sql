
create table [dbo].[providers_audit](
	[providers_audit_id] [varchar](200) NOT NULL,
	[auto_assignment] [varchar](1) NULL,
	[contract_id] [varchar](15) NULL,
	[member_id] [varchar](2) NULL,
	[pcp_effective_date] [varchar](12) NULL,
	[source_system] [varchar](5) NULL,
	[user_id] [varchar](15) NULL,
	[address_line1] [varchar](250) NULL,
	[address_line2] [varchar](250) NULL,
	[city] [varchar](50) NULL,
	[STATE] [varchar](20) NULL,
	[zip_code] [varchar](10) NULL,
	[creation_ts] [datetime2](7) NULL,
	[last_maint_ts] [datetime2](7) NULL,
	[response_json] [nvarchar](max) NULL,
 CONSTRAINT [pk_providers_audit] PRIMARY KEY CLUSTERED 
(
	[providers_audit_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
ALTER TABLE [dbo].[providers_audit] ADD  DEFAULT (getdate()) FOR [creation_ts]
GO
ALTER TABLE [dbo].[providers_audit] ADD  DEFAULT (getdate()) FOR [last_maint_ts]
GO