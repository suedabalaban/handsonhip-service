CREATE TABLE IF NOT EXISTS session (
    id BIGSERIAL PRIMARY KEY, -- Changed to BIGSERIAL for potential larger ID values
    memberID INTEGER NOT NULL,
    sessionId VARCHAR(255) NOT NULL,
    loginTime TIMESTAMP NOT NULL,
    CONSTRAINT fk_member FOREIGN KEY(memberID) REFERENCES member(memberID)
);