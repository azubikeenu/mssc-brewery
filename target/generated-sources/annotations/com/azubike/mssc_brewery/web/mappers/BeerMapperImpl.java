package com.azubike.mssc_brewery.web.mappers;

import com.azubike.mssc_brewery.web.domain.Beer;
import com.azubike.mssc_brewery.web.model.v2.BeerDtoV2;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-18T23:25:05+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.7 (Oracle Corporation)"
)
@Component
public class BeerMapperImpl implements BeerMapper {

    @Autowired
    private DateMapper dateMapper;

    @Override
    public BeerDtoV2 beerToBeerDto(Beer beer) {
        if ( beer == null ) {
            return null;
        }

        BeerDtoV2.BeerDtoV2Builder beerDtoV2 = BeerDtoV2.builder();

        beerDtoV2.id( beer.getId() );
        beerDtoV2.beerName( beer.getBeerName() );
        beerDtoV2.beerStyle( beer.getBeerStyle() );
        beerDtoV2.upc( beer.getUpc() );
        beerDtoV2.createdDate( dateMapper.asOffsetDateTime( beer.getCreatedDate() ) );
        beerDtoV2.lastModifiedDate( dateMapper.asOffsetDateTime( beer.getLastModifiedDate() ) );

        return beerDtoV2.build();
    }

    @Override
    public Beer beerDtoToBeer(BeerDtoV2 beerDto) {
        if ( beerDto == null ) {
            return null;
        }

        Beer.BeerBuilder beer = Beer.builder();

        beer.id( beerDto.getId() );
        beer.beerName( beerDto.getBeerName() );
        beer.beerStyle( beerDto.getBeerStyle() );
        beer.upc( beerDto.getUpc() );
        beer.createdDate( dateMapper.asTimestamp( beerDto.getCreatedDate() ) );
        beer.lastModifiedDate( dateMapper.asTimestamp( beerDto.getLastModifiedDate() ) );

        return beer.build();
    }
}
