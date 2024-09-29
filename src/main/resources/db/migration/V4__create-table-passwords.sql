CREATE TABLE tbPassword (
		id INT NOT NULL,
		userId INT NOT NULL,
		name VARCHAR(255) NOT NULL UNIQUE,
		content VARCHAR(255) NOT NULL,
		CONSTRAINT passwordId_PK PRIMARY KEY (id),
		CONSTRAINT tbPassword_userId_FK FOREIGN KEY (id) REFERENCES tbUser (id)
);

CREATE TABLE tb_password_folder (
		id INT NOT NULL,
		userId INT NOT NULL,
		folderId int NOT NULL,
		name VARCHAR(30) NOT NULL UNIQUE,
		content VARCHAR(30) NOT NULL,
		CONSTRAINT passwordFolderId_PK PRIMARY KEY (id),
		CONSTRAINT tbPasswordFolder_userId_FK FOREIGN KEY (id) REFERENCES tbUser (id),
        CONSTRAINT folderId_FK FOREIGN KEY (id) REFERENCES tbFolder (id)
);