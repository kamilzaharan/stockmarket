package pl.lodz.p.mocks;

import pl.lodz.p.model.Company;
import pl.lodz.p.model.CompanyStockValue;

import java.sql.Timestamp;
import java.util.LinkedHashSet;

/**
 * Created by Kaltair on 2016-08-09.
 */
public class ObjectMocks {

    public static CompanyStockValue TESTTEST_STOCK_VALUE = new CompanyStockValue();
    public static LinkedHashSet<CompanyStockValue> TESTTEST_STOCK_VALUE_LIST = new LinkedHashSet<>();
    public static Company TESTTEST = new Company();

    public static CompanyStockValue APPLE_STOCK_VALUE = new CompanyStockValue();
    public static LinkedHashSet<CompanyStockValue> APPLE_STOCK_VALUE_LIST = new LinkedHashSet<>();
    public static Company APPLE = new Company();

    public static CompanyStockValue NETFLIX_STOCK_VALUE = new CompanyStockValue();
    public static LinkedHashSet<CompanyStockValue> NETFLIX_STOCK_VALUE_LIST = new LinkedHashSet<>();
    public static Company NETFLIX = new Company();

    public static void CreateAllMocks() {
        CreateCompanyMocks();
        CreateStockValueListMocks();
        AddListToCompanyMock();
    }

    public static void CreateStockValueListMocks() {

        for (int i = 0; i < 10; i++) {
            Timestamp timestamp = new Timestamp(100, 10, i + 1, 12, 12, 29, 00);
            CompanyStockValue stock = new CompanyStockValue();
            stock.setChange(15.6);
            stock.setChangePercent(3.06);
            stock.setChangeYTD(532.17);
            stock.setChangePercentYTD(-1.443684937733);
            stock.setLastPrice(524.49 + i);
            stock.setTimestamp(timestamp.toString());
            stock.setHigh(52499);
            stock.setLow(519.175);
            stock.setOpen(519.175);
            stock.setCompanyId(APPLE);

            APPLE_STOCK_VALUE_LIST.add(stock);
        }

        for (int i = 10; i < 20; i++) {
            CompanyStockValue stock = new CompanyStockValue();
            Timestamp timestamp = new Timestamp(100, 10, i + 1, 12, 12, 29, 00);
            stock.setChange(11.6);
            stock.setChangePercent(2.06);
            stock.setChangeYTD(133.17);
            stock.setChangePercentYTD(-4.4432322937733);
            stock.setLastPrice(213.49 + i);
            stock.setHigh(11199);
            stock.setTimestamp(timestamp.toString());
            stock.setLow(299.175);
            stock.setOpen(333.175);
            stock.setCompanyId(NETFLIX);

            NETFLIX_STOCK_VALUE_LIST.add(stock);
        }
    }

    public static void CreateCompanyMocks() {
        TESTTEST.setSymbol("MSFT");
        TESTTEST.setFullName("Microsoft");

        APPLE.setSymbol("AAPL");
        APPLE.setFullName("AppleInc");

        NETFLIX.setSymbol("NFLX");
        NETFLIX.setFullName("NETFLIXInc");
    }

    public static void AddListToCompanyMock() {
        APPLE.setCompanyStockValueList(APPLE_STOCK_VALUE_LIST);
        NETFLIX.setCompanyStockValueList(NETFLIX_STOCK_VALUE_LIST);
    }

}
