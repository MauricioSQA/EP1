create table Clientes(

id int,
name varchar(255),
lastname varchar(255),
username varchar(20),
pass	varchar(100),
tipDoc  char(5),
nroDoc	varchar(20),
enable  int
)

ALTER TABLE Clientes ADD PRIMARY KEY (id) ;

ALTER TABLE Clientes MODIFY COLUMN id int auto_increment NOT NULL;

INSERT INTO Clientes (name,lastname,username,pass,tipDoc,nroDoc,enable)
VALUES ('Pedro','Lopez','DNI70563829','123456','DNI','70563829',1) ,
('Camila','Paredes','DNI56384920','123456','DNI','56384920',1),
('Andres','Ramirez','DNI8593489','123456','DNI','8593489',1),
('Ruben','Marquez','DNI73920495','123456','DNI','73920495',1),
('Lucia','Alvitez','DNI74725894','123456','DNI','74725894',1);

select * from Clientes;




create procedure listarAllClients()
begin
	select  * from Clientes ;
end

call listarAllClients();


