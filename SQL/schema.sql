CREATE TABLE Account(
	accountID SERIAL,
    name VARCHAR(45),
    phoneNumber VARCHAR(45),
    emailAddress VARCHAR(45) UNIQUE,
    password VARCHAR(45),
    CONSTRAINT PK_accountID PRIMARY KEY(accountID)
);

CREATE TABLE Customer(
	Account_accountID SERIAL,
    CONSTRAINT FK_Account_accountID FOREIGN KEY(Account_accountID) REFERENCES Account(accountID),
    CONSTRAINT PK_Account_accountID PRIMARY KEY(Account_accountID)
);

CREATE TABLE CreditCard(
	Customer_Account_accountID SERIAL,
    creditCardNumb CHAR(16),
    csv CHAR(3),
    expMonth VARCHAR(10),
    expDay VARCHAR(10),
    CONSTRAINT FK_Customer_Account_accountID FOREIGN KEY(Customer_Account_accountID) REFERENCES Customer(Account_accountID),
    CONSTRAINT PK_Customer_Account_accountID PRIMARY KEY(Customer_Account_accountID)
);

CREATE TABLE Address(
	Customer_Account_accountID SERIAL,
    streetName VARCHAR(30),
    city VARCHAR(30),
    zipcode INT,
    state CHAR(2),
    CONSTRAINT Fk_Customer_Acc_accountID FOREIGN KEY(Customer_Account_accountID) REFERENCES Customer(Account_accountID),
    CONSTRAINT Pk_Customer_Acc_accountID PRIMARY KEY(Customer_Account_accountID)
);

CREATE TABLE Membership(
	name VARCHAR(10),
    price decimal(5,2),
    CONSTRAINT PK_name PRIMARY KEY(name)
);

CREATE TABLE OwnedMembership(
	memberID SERIAL,
    startDate VARCHAR(10),
    renewalDate VARCHAR(10),
    status VARCHAR(10),
    Membership_name VARCHAR(10),
    CONSTRAINT PK_memberID PRIMARY KEY(memberID),
    CONSTRAINT FK_Membership_name FOREIGN KEY(Membership_name) REFERENCES Membership(name)
);

CREATE TABLE MembershipSale(
	purchaseID SERIAL,
    Customer_Account_accountID BIGINT UNSIGNED,
    OwnedMembership_memberID BIGINT UNSIGNED,
    OwnedMembership_Membership_name VARCHAR(20),
    totalAmount decimal(5, 2),
    purchaseTime VARCHAR(30),
    CONSTRAINT PK_purchaseID PRIMARY KEY(purchaseID),
    CONSTRAINT FK_Cust_Account_accountID FOREIGN KEY(Customer_Account_accountID) REFERENCES Customer(Account_accountID),
    CONSTRAINT FK_OwnedMembership_memberID FOREIGN KEY(OwnedMembership_memberID) REFERENCES OwnedMembership(memberID),
    CONSTRAINT FK_OwnedMembership_Membership_name FOREIGN KEY(OwnedMembership_Membership_name) REFERENCES OwnedMembership(Membership_name)
);

CREATE TABLE SalesPerson(
	Account_accountID SERIAL,
    CONSTRAINT PK_Sales_AccountID PRIMARY KEY(Account_accountID),
    CONSTRAINT FK_Sales_AccountID FOREIGN KEY(Account_accountID) REFERENCES Account(accountID)
);

CREATE TABLE Enrollment(
	OwnedMembership_memberID BIGINT UNSIGNED,
    Addon_addonID BIGINT UNSIGNED,
    OwnedMembership_Membership_name VARCHAR(20),
    CONSTRAINT PK_comp PRIMARY KEY(OwnedMembership_memberID, Addon_addonID),
    CONSTRAINT FK_memberID FOREIGN KEY(OwnedMembership_memberID) REFERENCES OwnedMembership(memberID),
    CONSTRAINT FK_Addon_addonID FOREIGN KEY(Addon_addonID) REFERENCES Addon(addonID),
    CONSTRAINT FK_membershipName FOREIGN KEY(OwnedMembership_Membership_name) REFERENCES OwnedMembership(Membership_name)
);

CREATE TABLE Addon(
	addonID SERIAL,
    name VARCHAR(20),
    description TEXT,
    CONSTRAINT PK_addonID PRIMARY KEY(addonID)
);

CREATE TABLE Class(
    Addon_addonID BIGINT UNSIGNED,
    classDate VARCHAR(10),
    timeSlot VARCHAR(10),
    instructorName VARCHAR(20),
    classLength TINYINT UNSIGNED,
    CONSTRAINT PK_addonID PRIMARY KEY(Addon_addonID),
    CONSTRAINT FK_addonID FOREIGN KEY(Addon_addonID) REFERENCES Addon(addonID)
);

CREATE TABLE PersonalTrainer(
	Addon_addonID BIGINT UNSIGNED,
    bookingDate VARCHAR(10),
    trainerName VARCHAR(20),
    CONSTRAINT PK_addID PRIMARY KEY(Addon_addonID),
    CONSTRAINT FK_addID FOREIGN KEY(Addon_addonID) REFERENCES Addon(addonID)
);