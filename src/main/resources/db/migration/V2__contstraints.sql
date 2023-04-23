ALTER TABLE works
    ADD CONSTRAINT non_negative_total_price CHECK (total_price >= 0);

ALTER TABLE employee
    ADD CONSTRAINT valid_avatar_url CHECK (avatar_url ~* '^https?://.*');

ALTER TABLE client_categories
    ADD CONSTRAINT unique_category_name UNIQUE (name);

CREATE FUNCTION uppercase_license_plate() RETURNS TRIGGER AS
$$
BEGIN
    NEW.license_plate = UPPER(NEW.license_plate);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER uppercase_license_plate_trigger
    BEFORE INSERT OR UPDATE
    ON vehicles
    FOR EACH ROW
EXECUTE FUNCTION uppercase_license_plate();
