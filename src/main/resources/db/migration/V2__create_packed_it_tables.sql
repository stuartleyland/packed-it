CREATE TABLE PACKING_LIST (
	ID INT AUTO_INCREMENT PRIMARY KEY,
	DESCRIPTION VARCHAR(200) NOT NULL,
	START_DATE DATE,
	END_DATE DATE,
	VERSION INT NOT NULL DEFAULT 0
);

CREATE TABLE CATEGORY (
  ID INT AUTO_INCREMENT PRIMARY KEY,
  DESCRIPTION VARCHAR(500)
);

CREATE TABLE ITEM (
  ID INT AUTO_INCREMENT PRIMARY KEY,
  DESCRIPTION VARCHAR(500),
  CATEGORY_ID INT NOT NULL,
  FOREIGN KEY (CATEGORY_ID) REFERENCES CATEGORY(ID)
);

CREATE TABLE LIST_ITEM (
  ID INT AUTO_INCREMENT PRIMARY KEY,
  LIST_ID INT NOT NULL,
  ITEM_ID INT NOT NULL,
  FOREIGN KEY (LIST_ID) REFERENCES PACKING_LIST(ID),
  FOREIGN KEY (ITEM_ID) REFERENCES ITEM(ID)
);