package pl.lodz.p.mocks;

import pl.lodz.p.model.Company;
import pl.lodz.p.model.CompanyStockValue;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Kaltair on 2016-08-09.
 */
public class ObjectMocks {

       public static CompanyStockValue APPLE_STOCK_VALUE =new CompanyStockValue();
       public static Set<CompanyStockValue> APPLE_STOCK_VALUE_LIST =new HashSet<>();
       public static Company APPLE = new Company();

    public static CompanyStockValue NETFLIX_STOCK_VALUE =new CompanyStockValue();
    public static Set<CompanyStockValue> NETFLIX_STOCK_VALUE_LIST =new HashSet<>();
    public static Company NETFLIX = new Company();

    public static void CreateAllMocks(){
        CreateCompanyMocks();
        CreateStockValueMocks();
        CreateStockValueListMocks();
        AddListToCompanyMock();
    }

    public static void CreateStockValueMocks() {

        APPLE_STOCK_VALUE.setId(1);
        APPLE_STOCK_VALUE.setChange(15.6);
        APPLE_STOCK_VALUE.setChangePercent(3.06);
        APPLE_STOCK_VALUE.setChangeYTD(532.17);
        APPLE_STOCK_VALUE.setChangePercentYTD(-1.443684937733);
        APPLE_STOCK_VALUE.setLastPrice(524.49);
        APPLE_STOCK_VALUE.setHigh(52499);
        APPLE_STOCK_VALUE.setLow(519.175);
        APPLE_STOCK_VALUE.setOpen(519.175);
        APPLE_STOCK_VALUE.setCompanyId(APPLE);


        NETFLIX_STOCK_VALUE.setId(2);
        NETFLIX_STOCK_VALUE.setChange(11.6);
        NETFLIX_STOCK_VALUE.setChangePercent(2.06);
        NETFLIX_STOCK_VALUE.setChangeYTD(133.17);
        NETFLIX_STOCK_VALUE.setChangePercentYTD(-4.4432322937733);
        NETFLIX_STOCK_VALUE.setLastPrice(213.49);
        NETFLIX_STOCK_VALUE.setHigh(11199);
        NETFLIX_STOCK_VALUE.setLow(299.175);
        NETFLIX_STOCK_VALUE.setOpen(333.175);
        NETFLIX_STOCK_VALUE.setCompanyId(NETFLIX);

    }

    public static void CreateStockValueListMocks(){

        for(int i=0; i<10; i++) {
            CompanyStockValue stock =new CompanyStockValue();
            stock.setId(i);
            stock.setChange(15.6);
            stock.setChangePercent(3.06);
            stock.setChangeYTD(532.17);
            stock.setChangePercentYTD(-1.443684937733);
            stock.setLastPrice(524.49+i);
            stock.setHigh(52499);
            stock.setLow(519.175);
            stock.setOpen(519.175);
            stock.setCompanyId(APPLE);

            APPLE_STOCK_VALUE_LIST.add(stock);
        }

        for(int i=10; i<20; i++) {
            CompanyStockValue stock =new CompanyStockValue();
            stock.setId(i);
            stock.setChange(11.6);
            stock.setChangePercent(2.06);
            stock.setChangeYTD(133.17);
            stock.setChangePercentYTD(-4.4432322937733);
            stock.setLastPrice(213.49+i);
            stock.setHigh(11199);
            stock.setLow(299.175);
            stock.setOpen(333.175);
            stock.setCompanyId(NETFLIX);

            NETFLIX_STOCK_VALUE_LIST.add(stock);
        }
    }

    public static void CreateCompanyMocks(){
        APPLE.setSymbol("APPL");
        APPLE.setId(1);
        APPLE.setFullName("AppleInc");

        NETFLIX.setSymbol("NFLX");
        NETFLIX.setId(2);
        NETFLIX.setFullName("NETFLIXInc");
    }

    public static void AddListToCompanyMock(){
        APPLE.setCompanyStockValueList(APPLE_STOCK_VALUE_LIST);
        NETFLIX.setCompanyStockValueList(NETFLIX_STOCK_VALUE_LIST);
    }

}
