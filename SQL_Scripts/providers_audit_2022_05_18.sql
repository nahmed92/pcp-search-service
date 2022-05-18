create table dbo.providers_audit
(
    id varchar(200) NOT NULL,
    request_json nvarchar(max) NULL,
    response_json nvarchar(max) NULL,
    created_at datetime DEFAULT getdate() NULL,
    last_updated_at datetime DEFAULT getdate() NULL,
    CONSTRAINT pk_providers_audit_id PRIMARY KEY (id)
);
