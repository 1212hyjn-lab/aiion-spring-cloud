-- Stadiums 테이블 생성
CREATE TABLE IF NOT EXISTS stadiums (
    id BIGSERIAL PRIMARY KEY,
    stadium_uk VARCHAR(255) UNIQUE NOT NULL,
    stadium_name VARCHAR(255),
    hometeam_uk VARCHAR(255),
    seat_count VARCHAR(255),
    address VARCHAR(255),
    ddd VARCHAR(255),
    tel VARCHAR(255)
);

-- Teams 테이블 생성
CREATE TABLE IF NOT EXISTS teams (
    id BIGSERIAL PRIMARY KEY,
    team_uk VARCHAR(255) UNIQUE NOT NULL,
    region_name VARCHAR(255),
    team_name VARCHAR(255),
    e_team_name VARCHAR(255),
    orig_yyyy VARCHAR(255),
    zip_code1 VARCHAR(255),
    zip_code2 VARCHAR(255),
    address VARCHAR(255),
    ddd VARCHAR(255),
    tel VARCHAR(255),
    fax VARCHAR(255),
    homepage VARCHAR(255),
    owner VARCHAR(255),
    stadium_uk VARCHAR(255),
    CONSTRAINT fk_team_stadium FOREIGN KEY (stadium_uk) REFERENCES stadiums(stadium_uk)
);

-- Players 테이블 생성
CREATE TABLE IF NOT EXISTS players (
    id BIGSERIAL PRIMARY KEY,
    player_uk VARCHAR(255),
    player_name VARCHAR(255),
    e_player_name VARCHAR(255),
    nickname VARCHAR(255),
    join_yyyy VARCHAR(255),
    position VARCHAR(255),
    back_no VARCHAR(255),
    nation VARCHAR(255),
    birth_date VARCHAR(255),
    solar VARCHAR(255),
    height VARCHAR(255),
    weight VARCHAR(255),
    team_uk VARCHAR(255),
    CONSTRAINT fk_player_team FOREIGN KEY (team_uk) REFERENCES teams(team_uk)
);

-- Schedules 테이블 생성
CREATE TABLE IF NOT EXISTS schedules (
    id BIGSERIAL PRIMARY KEY,
    sche_date VARCHAR(255),
    stadium_uk VARCHAR(255),
    gubun VARCHAR(255),
    hometeam_uk VARCHAR(255),
    awayteam_uk VARCHAR(255),
    home_score VARCHAR(255),
    away_score VARCHAR(255),
    CONSTRAINT fk_schedule_stadium FOREIGN KEY (stadium_uk) REFERENCES stadiums(stadium_uk),
    CONSTRAINT fk_schedule_hometeam FOREIGN KEY (hometeam_uk) REFERENCES teams(team_uk),
    CONSTRAINT fk_schedule_awayteam FOREIGN KEY (awayteam_uk) REFERENCES teams(team_uk)
);

-- 인덱스 생성
CREATE INDEX IF NOT EXISTS idx_stadium_uk ON stadiums(stadium_uk);
CREATE INDEX IF NOT EXISTS idx_team_uk ON teams(team_uk);
CREATE INDEX IF NOT EXISTS idx_player_team_uk ON players(team_uk);
CREATE INDEX IF NOT EXISTS idx_schedule_stadium_uk ON schedules(stadium_uk);
CREATE INDEX IF NOT EXISTS idx_schedule_hometeam_uk ON schedules(hometeam_uk);
CREATE INDEX IF NOT EXISTS idx_schedule_awayteam_uk ON schedules(awayteam_uk);

