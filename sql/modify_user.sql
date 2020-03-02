select * from user where email = 'ranahosur@gmail.com';
update user set username = null, password = null,active = 'Y',verification_token = null where email = 'ranahosur@gmail.com';
update user set email = 'ranahosur.a@gmail.com' where email = 'ranahosur@gmail.com';
