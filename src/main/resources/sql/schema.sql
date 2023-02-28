CREATE TABLE IF NOT EXISTS chat
(
    chat_id       serial PRIMARY KEY,
    group_name    varchar(200) NOT NULL,
    group_profile varchar,
    time_created  timestamp    NOT NULL DEFAULT NOW(),
    last_updated  timestamp    NOT NULL,

    CHECK ( last_updated >= time_created )
);

CREATE TABLE IF NOT EXISTS chat_message
(
    message_id     serial PRIMARY KEY,
    content        text      NOT NULL,
    time_sent      timestamp NOT NULL,
    sender_id      uuid      NOT NULL,
    target_chat_id integer   NOT NULL REFERENCES chat (chat_id)
);

CREATE TABLE IF NOT EXISTS user_group
(
    id      serial PRIMARY KEY,
    chat_id integer NOT NULL REFERENCES chat (chat_id),
    user_id uuid    NOT NULL,

    UNIQUE (chat_id, user_id)
);