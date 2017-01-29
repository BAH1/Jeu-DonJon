/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  elhadj
 * Created: 29 janv. 2017
 */

-- Table: public."JOUEUR"

-- DROP TABLE public."JOUEUR";

CREATE TABLE public."JOUEUR"
(
    pseudo character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "JOUEUR_pkey" PRIMARY KEY (pseudo)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."JOUEUR"
    OWNER to postgres;

-- Table: public."MESSAGEPIECE"

-- DROP TABLE public."MESSAGEPIECE";

CREATE TABLE public."MESSAGEPIECE"
(
    "pseudoP" character varying(30) COLLATE pg_catalog."default" NOT NULL,
    "numeroP" integer NOT NULL,
    message character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "MESSAGEPIECE_pkey" PRIMARY KEY (message, "pseudoP", "numeroP"),
    CONSTRAINT fk_message FOREIGN KEY ("pseudoP")
        REFERENCES public."JOUEUR" (pseudo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_message1 FOREIGN KEY ("numeroP")
        REFERENCES public."PIECE" ("numeroPi") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."MESSAGEPIECE"
    OWNER to postgres;

-- Index: fki_fk_message

-- DROP INDEX public.fki_fk_message;

CREATE INDEX fki_fk_message
    ON public."MESSAGEPIECE" USING btree
    (pseudoP COLLATE pg_catalog."default")
    TABLESPACE pg_default;

-- Index: fki_fk_message1

-- DROP INDEX public.fki_fk_message1;

CREATE INDEX fki_fk_message1
    ON public."MESSAGEPIECE" USING btree
    (numeroP)
    TABLESPACE pg_default;

-- Table: public."PIECE"

-- DROP TABLE public."PIECE";

CREATE TABLE public."PIECE"
(
    "numeroPi" integer NOT NULL,
    CONSTRAINT "PIECE_pkey" PRIMARY KEY ("numeroPi")
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."PIECE"
    OWNER to postgres;

-- Table: public."PORTE"

-- DROP TABLE public."PORTE";

CREATE TABLE public."PORTE"
(
    "nomPorte" character varying(6) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "PORTE_pkey" PRIMARY KEY ("nomPorte")
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."PORTE"
    OWNER to postgres;

-- Table: public."SETROUVER"

-- DROP TABLE public."SETROUVER";

CREATE TABLE public."SETROUVER"
(
    pseudopi character varying COLLATE pg_catalog."default" NOT NULL,
    numeropi integer NOT NULL,
    CONSTRAINT "SETROUVER_pkey" PRIMARY KEY (numeropi, pseudopi),
    CONSTRAINT fk_setrouver FOREIGN KEY (pseudopi)
        REFERENCES public."JOUEUR" (pseudo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_setrouver1 FOREIGN KEY (numeropi)
        REFERENCES public."PIECE" ("numeroPi") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."SETROUVER"
    OWNER to postgres;

-- Index: fki_fk_setrouver

-- DROP INDEX public.fki_fk_setrouver;

CREATE INDEX fki_fk_setrouver
    ON public."SETROUVER" USING btree
    (pseudopi COLLATE pg_catalog."default")
    TABLESPACE pg_default;

-- Index: fki_fk_setrouver1

-- DROP INDEX public.fki_fk_setrouver1;

CREATE INDEX fki_fk_setrouver1
    ON public."SETROUVER" USING btree
    (numeropi)
    TABLESPACE pg_default;


-- Table: public."TRAVERSER"

-- DROP TABLE public."TRAVERSER";

CREATE TABLE public."TRAVERSER"
(
    numerop integer NOT NULL,
    portepi character varying COLLATE pg_catalog."default" NOT NULL,
    numpidest integer NOT NULL,
    CONSTRAINT fk_traverser FOREIGN KEY (numerop)
        REFERENCES public."PIECE" ("numeroPi") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_traverser2 FOREIGN KEY (portepi)
        REFERENCES public."PORTE" ("nomPorte") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_traverser3 FOREIGN KEY (numpidest)
        REFERENCES public."PIECE" ("numeroPi") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."TRAVERSER"
    OWNER to postgres;

-- Index: fki_fk_traverser

-- DROP INDEX public.fki_fk_traverser;

CREATE INDEX fki_fk_traverser
    ON public."TRAVERSER" USING btree
    (numerop)
    TABLESPACE pg_default;

-- Index: fki_fk_traverser2

-- DROP INDEX public.fki_fk_traverser2;

CREATE INDEX fki_fk_traverser2
    ON public."TRAVERSER" USING btree
    (portepi COLLATE pg_catalog."default")
    TABLESPACE pg_default;

-- Index: fki_fk_traverser3

-- DROP INDEX public.fki_fk_traverser3;

CREATE INDEX fki_fk_traverser3
    ON public."TRAVERSER" USING btree
    (numpidest)
    TABLESPACE pg_default;