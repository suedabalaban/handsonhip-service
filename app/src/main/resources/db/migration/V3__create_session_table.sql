CREATE TABLE IF NOT EXISTS session (
    id BIGSERIAL PRIMARY KEY, -- Changed to BIGSERIAL for potential larger ID values
    memberID BIGINT NOT NULL,
    logintime TIMESTAMP NOT NULL,
    CONSTRAINT fk_member FOREIGN KEY(memberID) REFERENCES member(memberID)
);
