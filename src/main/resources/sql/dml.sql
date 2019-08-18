INSERT INTO town_for_every.town (name)
VALUES ('Минск'),
       ('Париж'),
       ('Новиград');

INSERT INTO town_for_every.town_information(town_id, description)
VALUES ((SELECT id FROM town_for_every.town WHERE id = 1),
        'Обязательно стоит взглянуть на Костел Святого Симона и Святой Елены (Красный костел).'),
       ((SELECT id FROM town_for_every.town WHERE id = 1),
        'Если хотите отдохнуть от суеты города, то вам стоит посетить Парк Победы.'),
       ((SELECT id FROM town_for_every.town WHERE id = 2),
        'На Эйфелевой башне всегда большие очереди, лучше посетите Сакре-Кёр.'),
       ((SELECT id FROM town_for_every.town WHERE id = 2),
        'Если хотите поглазеть на прекрасное, то вам стоит посетите Лувр.'),
       ((SELECT id FROM town_for_every.town WHERE id = 3),
        'Обязательно посетите местный рынок, только держите свой кошелек на виду.');
