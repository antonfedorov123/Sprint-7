--liquibase formatted sql

--changeset avfedorov:index

create index index_account ON account1(id);