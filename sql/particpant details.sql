select * from user u
join user_role ur on ur.user_id = u.user_id
join role r on r.role_id = ur.role_id
;
update user set email = 'ranahosur@gmail.com',username = null,password = null where user_id  = '7c218ad16e4e461d8a6737fb185e23d2';

select * from user u
join user_role ur on ur.user_id = u.user_id
join role r on r.role_id = ur.role_id
join participant p on p.user_id = u.user_id
join participant_transaction pt on pt.participant_id = p.participant_id
where
u.username = 'raghu'
u.email = 'ranahosur@gmail.com'
;
