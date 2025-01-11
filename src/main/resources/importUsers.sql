INSERT INTO public.role (name) VALUES ('ROLE_USER');
INSERT INTO public.role (name) VALUES ('ROLE_ADMIN');

INSERT INTO public.users (name, last_name, password) VALUES ('user', 'user', '$2a$10$9hRVLhejwyNUaK31YyvEgOCkmfCwTBxuSLaINeXcEZU3bkPGz7euu');
INSERT INTO public.users (name, last_name, password) VALUES ('admin', 'admin', '$2a$10$C0wKuk0Jba/s3Uy/aBHyL.ZdInLzsUt9emmqVjcxFJwZ/VDYEdSz.');


INSERT INTO public.users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO public.users_roles (user_id, role_id) VALUES (2, 1);
INSERT INTO public.users_roles (user_id, role_id) VALUES (2, 2);