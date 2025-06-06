-- data.sql

DELETE FROM goal;
DELETE FROM match;
DELETE FROM player;
DELETE FROM club;

-- Dodaj kluby
INSERT INTO club (id, name, budget, points, goal_balance, coach_surname) VALUES
(1, 'FC Barcelona', 150000000, 3, 2, 'Xavi Hernandez'),
(2, 'Real Madryt', 180000000, 0, -2, 'Carlo Ancelotti');

-- Dodaj zawodników (zauważ, że club_id odwołuje się do id klubów)
INSERT INTO player (id, first_name, last_name, position, better_foot, goals, assists, club_id) VALUES
(1, 'Robert', 'Lewandowski', 'S', 'R', 10, 3, 1), -- Barcelona
(2, 'Pedri', 'Gonzalez', 'M', 'R', 5, 8, 1),    -- Barcelona
(3, 'Vinicius', 'Junior', 'S', 'R', 12, 6, 2),  -- Real Madryt
(4, 'Jude', 'Bellingham', 'M', 'R', 15, 5, 2);   -- Real Madryt

-- Dodaj mecze (mecz i rewanż)
-- Mecz 1: FC Barcelona (id:1) vs Real Madryt (id:2)
INSERT INTO match (id, round_number, host_id, guest_id, date) VALUES
(1, 1, 1, 2, '2025-09-01 20:00:00');

-- Mecz 2: Real Madryt (id:2) vs FC Barcelona (id:1) (rewanż)
INSERT INTO match (id, round_number, host_id, guest_id, date) VALUES
(2, 20, 2, 1, '2026-02-15 21:00:00');

-- Dodaj bramki dla meczu 1 (FC Barcelona vs Real Madryt: 2-0)
INSERT INTO goal (id, match_id, goal_minute, scorer_id, assistant_id) VALUES
(1, 1, 30, 1, 2), -- Lewandowski (Barcelona) asystował Pedri (Barcelona)
(2, 1, 65, 1, NULL); -- Lewandowski (Barcelona) bez asysty

-- Dodaj bramki dla meczu 2 (Real Madryt vs FC Barcelona: 2-1)
INSERT INTO goal (id, match_id, goal_minute, scorer_id, assistant_id) VALUES
(3, 2, 10, 3, 4), -- Vinicius (Real) asystował Bellingham (Real)
(4, 2, 45, 4, NULL), -- Bellingham (Real) bez asysty
(5, 2, 80, 2, 1); -- Pedri (Barcelona) asystował Lewandowski (Barcelona)

-- FC Barcelona (id:1):
-- Mecz 1: wygrana 2-0 (3 punkty, bilans +2)
-- Mecz 2: przegrana 2-1 (0 punktów, bilans -1)
-- Suma: 3 punkty, bilans +1
UPDATE club SET points = 3, goal_balance = 1 WHERE id = 1;

-- Real Madryt (id:2):
-- Mecz 1: przegrana 0-2 (0 punktów, bilans -2)
-- Mecz 2: wygrana 2-1 (3 punkty, bilans +1)
-- Suma: 3 punkty, bilans -1
UPDATE club SET points = 3, goal_balance = -1 WHERE id = 2;

-- Aktualizuj gole i asysty zawodników
-- Lewandowski (id:1): 2 gole, 1 asysta
UPDATE player SET goals = 2, assists = 1 WHERE id = 1;
-- Pedri (id:2): 1 gol, 1 asysta
UPDATE player SET goals = 1, assists = 1 WHERE id = 2;
-- Vinicius (id:3): 1 gol
UPDATE player SET goals = 1, assists = 0 WHERE id = 3;
-- Bellingham (id:4): 1 gol, 1 asysta
UPDATE player SET goals = 1, assists = 1 WHERE id = 4;