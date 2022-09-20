insert into bootstrap_db.roles (id, name)
values  (1, 'ROLE_USER'),
        (2, 'ROLE_ADMIN');
        
insert into bootstrap_db.users (id, first_name, last_name, age, email, password)
values  (1, 'Admin', 'Adminov', 30, 'admin@mail.ru', '$2a$12$XWbC1/ikuINdtHZphEay4OR4kfUITBQX5rFeR5Nsy1KOORfBYAj8S'),
        (2, 'User', 'Userov', 25, 'user@mail.ru', '$2a$12$lvabNdhFtGv.LClJqAcpEubeamtM21Vwq9gJ.Vyzwg75o5sxMfxL2'),
        (3, 'Test', 'Testov', 20, 'test@mail.ru', '$2a$12$H.Y8JgPPQpREXrsDHgJ7ZuXkXSbxIvfYOZKLPvNrbD7mVoHR/IwxG');
        
insert into bootstrap_db.users_roles (user_id, role_id)
values  (2, 1),
        (3, 1),
        (1, 2),
        (1, 1);        
