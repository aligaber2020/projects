/**
 * CREATE Script for init of DB
 */

---Create 3 Security Users------------------------------------------
--insert into securityuser (id, date_created, deleted, user_name, password) values (1, now(), false, 'admin', '$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu');

--insert into securityuser (id, date_created, deleted, user_name, password) values (2, now(), false, 'admin2', '$2a$10$J4KgkvwdsKPQcZ9Aw2Q8A.WIQ0whpLWJjJ9NkC6.bHAtM/zwQCPMS');

--insert into securityuser (id, date_created, deleted, user_name, password) values (3, now(), false, 'admin3', '$2a$10$YJzfTttaK2STBjf0xvChKOddBZN4zHdXhSRnfVqOy3eH/0KY6r0SS');

---Create 3 Security Roles------------------------------------------
--insert into securityrole (id, date_created, deleted, role_name) values (1, now(), false, 'ROLE_User_Access');

--insert into securityrole (id, date_created, deleted, role_name) values (2, now(), false, 'ROLE_Admin_Access');

--insert into securityrole (id, date_created, deleted, role_name) values (3, now(), false, 'ROLE_Root_Access');

--Create User Roles Relation------------------------------------------
--insert into securityuserrole (id, date_created, deleted, user_id, role_id) values (1, now(), false, 1, 1);

--insert into securityuserrole (id, date_created, deleted, user_id, role_id) values (2, now(), false, 2, 2);

--insert into securityuserrole (id, date_created, deleted, user_id, role_id) values (3, now(), false, 3, 1);

--insert into securityuserrole (id, date_created, deleted, user_id, role_id) values (4, now(), false, 3, 2);

--insert into securityuserrole (id, date_created, deleted, user_id, role_id) values (5, now(), false, 3, 3);
-------------- loockups tables--------------------------------------

