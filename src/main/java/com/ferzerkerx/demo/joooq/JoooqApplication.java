package com.ferzerkerx.demo.joooq;

import com.ferzerkerx.demo.joooq.db.Tables;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JoooqApplication implements CommandLineRunner {

    private static final Logger LOG =  LoggerFactory.getLogger(JoooqApplication.class);

    @Autowired
    private DSLContext dslContext;

	@Override
	public void run(String... strings) {
        Result<Record> result = dslContext.select().from(Tables.ARTIST).fetch();

        for (Record r : result) {
            Integer id = r.getValue(Tables.ARTIST.ARTIST_ID);
            String name = r.getValue(Tables.ARTIST.NAME);

            LOG.info("id:{} name:{}", id, name);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(JoooqApplication.class, args);
    }
}
