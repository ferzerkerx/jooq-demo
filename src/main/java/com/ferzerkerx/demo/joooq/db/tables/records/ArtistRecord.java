/*
 * This file is generated by jOOQ.
*/
package com.ferzerkerx.demo.joooq.db.tables.records;


import com.ferzerkerx.demo.joooq.db.tables.Artist;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ArtistRecord extends UpdatableRecordImpl<ArtistRecord> implements Record2<Integer, String> {

    private static final long serialVersionUID = -1679529871;

    /**
     * Setter for <code>PUBLIC.ARTIST.ARTIST_ID</code>.
     */
    public void setArtistId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>PUBLIC.ARTIST.ARTIST_ID</code>.
     */
    public Integer getArtistId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>PUBLIC.ARTIST.NAME</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>PUBLIC.ARTIST.NAME</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<Integer, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<Integer, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return Artist.ARTIST.ARTIST_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Artist.ARTIST.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getArtistId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArtistRecord value1(Integer value) {
        setArtistId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArtistRecord value2(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArtistRecord values(Integer value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ArtistRecord
     */
    public ArtistRecord() {
        super(Artist.ARTIST);
    }

    /**
     * Create a detached, initialised ArtistRecord
     */
    public ArtistRecord(Integer artistId, String name) {
        super(Artist.ARTIST);

        set(0, artistId);
        set(1, name);
    }
}
