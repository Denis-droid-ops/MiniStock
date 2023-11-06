CREATE TABLE Element(
    id SERIAL PRIMARY KEY,
    name VARCHAR UNIQUE NOT NULL CONSTRAINT min_max_sym_count CHECK (length(name)>=3 AND length(name)<=100),
    price INTEGER NOT NULL CONSTRAINT positive_price CHECK (price > 0 AND price<400000),
    count INTEGER NOT NULL
);

CREATE TABLE Moving(
    id SERIAL PRIMARY KEY,
    date TIMESTAMP NOT NULL,
    movable_count INTEGER NOT NULL CONSTRAINT positive_price CHECK (movable_count > -1000 AND movable_count<1000),
    remain_after INTEGER NOT NULL,
    element_id INTEGER NOT NULL,
    FOREIGN KEY (element_id) REFERENCES Element(id) ON DELETE CASCADE
)