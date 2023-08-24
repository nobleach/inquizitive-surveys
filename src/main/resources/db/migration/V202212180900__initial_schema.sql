CREATE TABLE "surveys" (
  "id" uuid CONSTRAINT surveys_id_pkey PRIMARY KEY,
  "name" text,
  "start_date" TIMESTAMPTZ,
  "end_date" TIMESTAMPTZ,
  "is_active" boolean,
  "starting_question_id" uuid,
  "created_at" TIMESTAMPTZ DEFAULT (now()),
  "updated_at" TIMESTAMPTZ DEFAULT (now())
);

CREATE TABLE "questions" (
  "id" uuid CONSTRAINT questions_id_pkey PRIMARY KEY,
  "question_text" text,
  "response_type_id" uuid,
  "question_aid_types_id" uuid,
  "question_aid_url" text,
  "created_at" TIMESTAMPTZ DEFAULT (now()),
  "updated_at" TIMESTAMPTZ DEFAULT (now())
);

CREATE TABLE "question_aid_types" (
  "id" uuid CONSTRAINT question_aid_types_id_pkey PRIMARY KEY,
  "label" text,
  "created_at" TIMESTAMPTZ DEFAULT (now()),
  "updated_at" TIMESTAMPTZ DEFAULT (now())
);

CREATE TABLE "responses" (
  "id" uuid CONSTRAINT responses_id_pkey PRIMARY KEY,
  "question_id" uuid,
  "label" text,
  "created_at" TIMESTAMPTZ DEFAULT (now()),
  "updated_at" TIMESTAMPTZ DEFAULT (now())
);

CREATE TABLE "response_types" (
  "id" uuid CONSTRAINT response_types_id_pkey PRIMARY KEY,
  "name" text,
  "created_at" TIMESTAMPTZ DEFAULT (now()),
  "updated_at" TIMESTAMPTZ DEFAULT (now())
);

CREATE TABLE "question_plans" (
  "id" uuid CONSTRAINT question_plans_types_id_pkey PRIMARY KEY,
  "survey_id" uuid,
  "response_id" uuid,
  "next_question_id" uuid,
  "created_at" TIMESTAMPTZ DEFAULT (now()),
  "updated_at" TIMESTAMPTZ DEFAULT (now())
);

ALTER TABLE "surveys" ADD FOREIGN KEY ("starting_question_id") REFERENCES "questions" ("id");

ALTER TABLE "questions" ADD FOREIGN KEY ("question_aid_types_id") REFERENCES "question_aid_types" ("id");

ALTER TABLE "responses" ADD FOREIGN KEY ("question_id") REFERENCES "questions" ("id");

ALTER TABLE "questions" ADD FOREIGN KEY ("response_type_id") REFERENCES "response_types" ("id");

ALTER TABLE "question_plans" ADD FOREIGN KEY ("survey_id") REFERENCES "surveys" ("id");

ALTER TABLE "question_plans" ADD FOREIGN KEY ("response_id") REFERENCES "responses" ("id");

ALTER TABLE "question_plans" ADD FOREIGN KEY ("next_question_id") REFERENCES "questions" ("id");
