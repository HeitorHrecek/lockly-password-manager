CREATE TABLE tbFolder (
		folderId INT NOT NULL,
		userId INT NOT NULL,
		name VARCHAR(255) NOT NULL UNIQUE,
		CONSTRAINT folderId_PK PRIMARY KEY (folderId, userId),
		CONSTRAINT tbFolder_userId_FK FOREIGN KEY (userId) REFERENCES tbUser (userId)
);