/*
 * This file is generated by jOOQ.
*/
package com.ferzerkerx.demo.joooq.db.tables;


import com.ferzerkerx.demo.joooq.db.Keys;
import com.ferzerkerx.demo.joooq.db.Public;
import com.ferzerkerx.demo.joooq.db.tables.records.ArtistRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


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
public class Artist extends TableImpl<ArtistRecord> {

    private static final long serialVersionUID = 1436777614;

    /**
     * The reference instance of <code>PUBLIC.ARTIST</code>
     */
    public static final Artist ARTIST = new Artist();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ArtistRecord> getRecordType() {
        return ArtistRecord.class;
    }

    /**
     * The column <code>PUBLIC.ARTIST.ARTIST_ID</code>.
     */
    public final TableField<ArtistRecord, Integer> ARTIST_ID = createField("ARTIST_ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>PUBLIC.ARTIST.NAME</code>.
     */
    public final TableField<ArtistRecord, String> NAME = createField("NAME", org.jooq.impl.SQLDataType.VARCHAR.length(100).nullable(false), this, "");

    /**
     * Create a <code>PUBLIC.ARTIST</code> table reference
     */
    public Artist() {
        this("ARTIST", null);
    }

    /**
     * Create an aliased <code>PUBLIC.ARTIST</code> table reference
     */
    public Artist(String alias) {
        this(alias, ARTIST);
    }

    private Artist(String alias, Table<ArtistRecord> aliased) {
        this(alias, aliased, null);
    }

    private Artist(String alias, Table<ArtistRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<ArtistRecord, Integer> getIdentity() {
        return Keys.IDENTITY_ARTIST;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<ArtistRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_7;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<ArtistRecord>> getKeys() {
        return Arrays.<UniqueKey<ArtistRecord>>asList(Keys.CONSTRAINT_7);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Artist as(String alias) {
        return new Artist(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Artist rename(String name) {
        return new Artist(name, null);
    }
}
