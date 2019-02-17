package com.example.arahnaka.myapplication;

import java.util.ArrayList;
import java.util.List;

public class CurrencyExchange {

    /**
     * success : true
     * timestamp : 1549962848
     * base : EUR
     * date : 2019-02-12
     * rates : {"AED":4.13878,"AFN":85.126539,"ALL":124.200065,"AMD":553.953738,"ANG":2.028968,"AOA":352.834309,"ARS":42.740447,"AUD":1.591288,"AWG":2.021926,"AZN":1.918269,"BAM":1.954319,"BBD":2.262541,"BDT":94.598407,"BGN":1.955678,"BHD":0.424836,"BIF":2034.940116,"BMD":1.126735,"BND":1.521771,"BOB":7.789853,"BRL":4.233821,"BSD":1.127355,"BTC":3.14E-4,"BTN":79.696272,"BWP":11.808423,"BYN":2.438311,"BYR":22084.010004,"BZD":2.272061,"CAD":1.4972,"CDF":1836.578548,"CHF":1.134189,"CLF":0.028222,"CLP":746.490223,"CNY":7.639035,"COP":3533.27259,"CRC":691.742214,"CUC":1.126735,"CUP":29.858483,"CVE":109.778374,"CZK":25.847917,"DJF":200.243651,"DKK":7.462503,"DOP":57.046989,"DZD":134.188535,"EGP":19.847433,"ERN":16.90104,"ETB":32.118706,"EUR":1,"FJD":2.41279,"FKP":0.878763,"GBP":0.876893,"GEL":2.980243,"GGP":0.877041,"GHS":5.814387,"GIP":0.879026,"GMD":55.80156,"GNF":10318.922904,"GTQ":8.767183,"GYD":236.840197,"HKD":8.843125,"HNL":27.596558,"HRK":7.405239,"HTG":93.319568,"HUF":318.56177,"IDR":15842.46034,"ILS":4.107998,"IMP":0.877041,"INR":79.677056,"IQD":1348.927387,"IRR":47441.185553,"ISK":136.650203,"JEP":0.877041,"JMD":152.311728,"JOD":0.798637,"JPY":124.600014,"KES":113.022433,"KGS":78.659978,"KHR":4539.055703,"KMF":491.960734,"KPW":1014.108854,"KRW":1266.786073,"KWD":0.342644,"KYD":0.941956,"KZT":422.761987,"LAK":9685.584748,"LBP":1701.426458,"LKR":200.829397,"LRD":181.122185,"LSL":15.346327,"LTL":3.326956,"LVL":0.68155,"LYD":1.570387,"MAD":10.739195,"MDL":19.368959,"MGA":4042.331856,"MKD":61.581715,"MMK":1740.244483,"MNT":2963.175173,"MOP":9.138044,"MRO":402.244199,"MUR":38.905967,"MVR":17.407671,"MWK":820.268874,"MXN":21.717256,"MYR":4.593589,"MZN":70.201239,"NAD":15.34569,"NGN":406.751207,"NIO":37.137761,"NOK":9.804169,"NPR":128.695476,"NZD":1.674723,"OMR":0.433771,"PAB":1.127355,"PEN":3.753437,"PGK":3.796816,"PHP":58.731096,"PKR":157.480227,"PLN":4.328072,"PYG":6824.076291,"QAR":4.10245,"RON":4.742092,"RSD":118.115686,"RUB":74.064878,"RWF":1014.287031,"SAR":4.22475,"SBD":9.081372,"SCR":15.402511,"SDG":53.826385,"SEK":10.46577,"SGD":1.531347,"SHP":1.488306,"SLL":9689.922614,"SOS":653.506147,"SRD":8.403164,"STD":23718.451652,"SVC":9.892453,"SYP":580.269003,"SZL":15.535405,"THB":35.312035,"TJS":10.654915,"TMT":3.954841,"TND":3.365544,"TOP":2.558857,"TRY":5.950694,"TTD":7.634589,"TWD":34.742315,"TZS":2619.769737,"UAH":30.55255,"UGX":4149.033312,"USD":1.126735,"UYU":36.742889,"UZS":9490.433786,"VEF":11.253271,"VND":26145.327048,"VUV":129.048168,"WST":2.95427,"XAF":655.365928,"XAG":0.071297,"XAU":8.58E-4,"XCD":3.045058,"XDR":0.812703,"XOF":655.365466,"XPF":119.163783,"YER":281.486626,"ZAR":15.5574,"ZMK":10141.956724,"ZMW":13.313501,"ZWL":363.208739}
     */

    private boolean success;
    private int timestamp;
    private String base;
    private String date;
    private RatesBean rates;

    public List<Currency> getCurrencyList(){
        List<Currency> currencyList = new ArrayList<>();
        currencyList.add(new Currency("USD", rates.getUSD()));
        currencyList.add(new Currency("MYR", rates.getMYR()));
        currencyList.add(new Currency("SGD", rates.getSGD()));
        currencyList.add(new Currency("INR", rates.getINR()));
        currencyList.add(new Currency("CAD", rates.getCAD()));
        currencyList.add(new Currency("CNY", rates.getCNY()));
        currencyList.add(new Currency("KHR", rates.getKHR()));
        currencyList.add(new Currency("MMK", rates.getMMK()));
        currencyList.add(new Currency("LAK", rates.getLAK()));
        currencyList.add(new Currency("VND", rates.getVND()));

        return  currencyList;
    }
    public List<Currency> getCurrencyList2(){
        List<Currency> currencyList2 = new ArrayList<>();
        currencyList2.add(new Currency("USD", rates.getUSD()));
        currencyList2.add(new Currency("GBP", rates.getGBP()));
        currencyList2.add(new Currency("EUR", rates.getEUR()));
        currencyList2.add(new Currency("JPY", rates.getJPY()));
        currencyList2.add(new Currency("HKD", rates.getHKD()));
        currencyList2.add(new Currency("MYR", rates.getMYR()));
        currencyList2.add(new Currency("SGD", rates.getSGD()));
        currencyList2.add(new Currency("BND", rates.getBND()));
        currencyList2.add(new Currency("CNY", rates.getCNY()));
        currencyList2.add(new Currency("IDR", rates.getIDR()));
        currencyList2.add(new Currency("MMK", rates.getMMK()));
        currencyList2.add(new Currency("INR", rates.getINR()));
        currencyList2.add(new Currency("KRW", rates.getKRW()));
        currencyList2.add(new Currency("LAK", rates.getLAK()));
        currencyList2.add(new Currency("PHP", rates.getPHP()));
        currencyList2.add(new Currency("TWD", rates.getTWD()));
        currencyList2.add(new Currency("AUD", rates.getAUD()));
        currencyList2.add(new Currency("NZD", rates.getNZD()));
        currencyList2.add(new Currency("CHF", rates.getCHF()));
        currencyList2.add(new Currency("DKK", rates.getDKK()));
        currencyList2.add(new Currency("NOK", rates.getNOK()));
        currencyList2.add(new Currency("SEK", rates.getSEK()));
        currencyList2.add(new Currency("CAD", rates.getCAD()));
        currencyList2.add(new Currency("RUB", rates.getRUB()));
        currencyList2.add(new Currency("VND", rates.getVND()));
        currencyList2.add(new Currency("ZAR", rates.getZAR()));
        currencyList2.add(new Currency("AED", rates.getAED()));
        currencyList2.add(new Currency("BHD", rates.getBHD()));
        currencyList2.add(new Currency("OMR", rates.getOMR()));
        currencyList2.add(new Currency("QAR", rates.getQAR()));
        currencyList2.add(new Currency("SAR", rates.getSAR()));
        currencyList2.add(new Currency("KHR", rates.getKHR()));

        return  currencyList2;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public RatesBean getRates() {
        return rates;
    }

    public void setRates(RatesBean rates) {
        this.rates = rates;
    }

    public static class RatesBean {
        /**
         * AED : 4.13878
         * AFN : 85.126539
         * ALL : 124.200065
         * AMD : 553.953738
         * ANG : 2.028968
         * AOA : 352.834309
         * ARS : 42.740447
         * AUD : 1.591288
         * AWG : 2.021926
         * AZN : 1.918269
         * BAM : 1.954319
         * BBD : 2.262541
         * BDT : 94.598407
         * BGN : 1.955678
         * BHD : 0.424836
         * BIF : 2034.940116
         * BMD : 1.126735
         * BND : 1.521771
         * BOB : 7.789853
         * BRL : 4.233821
         * BSD : 1.127355
         * BTC : 3.14E-4
         * BTN : 79.696272
         * BWP : 11.808423
         * BYN : 2.438311
         * BYR : 22084.010004
         * BZD : 2.272061
         * CAD : 1.4972
         * CDF : 1836.578548
         * CHF : 1.134189
         * CLF : 0.028222
         * CLP : 746.490223
         * CNY : 7.639035
         * COP : 3533.27259
         * CRC : 691.742214
         * CUC : 1.126735
         * CUP : 29.858483
         * CVE : 109.778374
         * CZK : 25.847917
         * DJF : 200.243651
         * DKK : 7.462503
         * DOP : 57.046989
         * DZD : 134.188535
         * EGP : 19.847433
         * ERN : 16.90104
         * ETB : 32.118706
         * EUR : 1
         * FJD : 2.41279
         * FKP : 0.878763
         * GBP : 0.876893
         * GEL : 2.980243
         * GGP : 0.877041
         * GHS : 5.814387
         * GIP : 0.879026
         * GMD : 55.80156
         * GNF : 10318.922904
         * GTQ : 8.767183
         * GYD : 236.840197
         * HKD : 8.843125
         * HNL : 27.596558
         * HRK : 7.405239
         * HTG : 93.319568
         * HUF : 318.56177
         * IDR : 15842.46034
         * ILS : 4.107998
         * IMP : 0.877041
         * INR : 79.677056
         * IQD : 1348.927387
         * IRR : 47441.185553
         * ISK : 136.650203
         * JEP : 0.877041
         * JMD : 152.311728
         * JOD : 0.798637
         * JPY : 124.600014
         * KES : 113.022433
         * KGS : 78.659978
         * KHR : 4539.055703
         * KMF : 491.960734
         * KPW : 1014.108854
         * KRW : 1266.786073
         * KWD : 0.342644
         * KYD : 0.941956
         * KZT : 422.761987
         * LAK : 9685.584748
         * LBP : 1701.426458
         * LKR : 200.829397
         * LRD : 181.122185
         * LSL : 15.346327
         * LTL : 3.326956
         * LVL : 0.68155
         * LYD : 1.570387
         * MAD : 10.739195
         * MDL : 19.368959
         * MGA : 4042.331856
         * MKD : 61.581715
         * MMK : 1740.244483
         * MNT : 2963.175173
         * MOP : 9.138044
         * MRO : 402.244199
         * MUR : 38.905967
         * MVR : 17.407671
         * MWK : 820.268874
         * MXN : 21.717256
         * MYR : 4.593589
         * MZN : 70.201239
         * NAD : 15.34569
         * NGN : 406.751207
         * NIO : 37.137761
         * NOK : 9.804169
         * NPR : 128.695476
         * NZD : 1.674723
         * OMR : 0.433771
         * PAB : 1.127355
         * PEN : 3.753437
         * PGK : 3.796816
         * PHP : 58.731096
         * PKR : 157.480227
         * PLN : 4.328072
         * PYG : 6824.076291
         * QAR : 4.10245
         * RON : 4.742092
         * RSD : 118.115686
         * RUB : 74.064878
         * RWF : 1014.287031
         * SAR : 4.22475
         * SBD : 9.081372
         * SCR : 15.402511
         * SDG : 53.826385
         * SEK : 10.46577
         * SGD : 1.531347
         * SHP : 1.488306
         * SLL : 9689.922614
         * SOS : 653.506147
         * SRD : 8.403164
         * STD : 23718.451652
         * SVC : 9.892453
         * SYP : 580.269003
         * SZL : 15.535405
         * THB : 35.312035
         * TJS : 10.654915
         * TMT : 3.954841
         * TND : 3.365544
         * TOP : 2.558857
         * TRY : 5.950694
         * TTD : 7.634589
         * TWD : 34.742315
         * TZS : 2619.769737
         * UAH : 30.55255
         * UGX : 4149.033312
         * USD : 1.126735
         * UYU : 36.742889
         * UZS : 9490.433786
         * VEF : 11.253271
         * VND : 26145.327048
         * VUV : 129.048168
         * WST : 2.95427
         * XAF : 655.365928
         * XAG : 0.071297
         * XAU : 8.58E-4
         * XCD : 3.045058
         * XDR : 0.812703
         * XOF : 655.365466
         * XPF : 119.163783
         * YER : 281.486626
         * ZAR : 15.5574
         * ZMK : 10141.956724
         * ZMW : 13.313501
         * ZWL : 363.208739
         */

        private double AED;
        private double AFN;
        private double ALL;
        private double AMD;
        private double ANG;
        private double AOA;
        private double ARS;
        private double AUD;
        private double AWG;
        private double AZN;
        private double BAM;
        private double BBD;
        private double BDT;
        private double BGN;
        private double BHD;
        private double BIF;
        private double BMD;
        private double BND;
        private double BOB;
        private double BRL;
        private double BSD;
        private double BTC;
        private double BTN;
        private double BWP;
        private double BYN;
        private double BYR;
        private double BZD;
        private double CAD;
        private double CDF;
        private double CHF;
        private double CLF;
        private double CLP;
        private double CNY;
        private double COP;
        private double CRC;
        private double CUC;
        private double CUP;
        private double CVE;
        private double CZK;
        private double DJF;
        private double DKK;
        private double DOP;
        private double DZD;
        private double EGP;
        private double ERN;
        private double ETB;
        private int EUR;
        private double FJD;
        private double FKP;
        private double GBP;
        private double GEL;
        private double GGP;
        private double GHS;
        private double GIP;
        private double GMD;
        private double GNF;
        private double GTQ;
        private double GYD;
        private double HKD;
        private double HNL;
        private double HRK;
        private double HTG;
        private double HUF;
        private double IDR;
        private double ILS;
        private double IMP;
        private double INR;
        private double IQD;
        private double IRR;
        private double ISK;
        private double JEP;
        private double JMD;
        private double JOD;
        private double JPY;
        private double KES;
        private double KGS;
        private double KHR;
        private double KMF;
        private double KPW;
        private double KRW;
        private double KWD;
        private double KYD;
        private double KZT;
        private double LAK;
        private double LBP;
        private double LKR;
        private double LRD;
        private double LSL;
        private double LTL;
        private double LVL;
        private double LYD;
        private double MAD;
        private double MDL;
        private double MGA;
        private double MKD;
        private double MMK;
        private double MNT;
        private double MOP;
        private double MRO;
        private double MUR;
        private double MVR;
        private double MWK;
        private double MXN;
        private double MYR;
        private double MZN;
        private double NAD;
        private double NGN;
        private double NIO;
        private double NOK;
        private double NPR;
        private double NZD;
        private double OMR;
        private double PAB;
        private double PEN;
        private double PGK;
        private double PHP;
        private double PKR;
        private double PLN;
        private double PYG;
        private double QAR;
        private double RON;
        private double RSD;
        private double RUB;
        private double RWF;
        private double SAR;
        private double SBD;
        private double SCR;
        private double SDG;
        private double SEK;
        private double SGD;
        private double SHP;
        private double SLL;
        private double SOS;
        private double SRD;
        private double STD;
        private double SVC;
        private double SYP;
        private double SZL;
        private double THB;
        private double TJS;
        private double TMT;
        private double TND;
        private double TOP;
        private double TRY;
        private double TTD;
        private double TWD;
        private double TZS;
        private double UAH;
        private double UGX;
        private double USD;
        private double UYU;
        private double UZS;
        private double VEF;
        private double VND;
        private double VUV;
        private double WST;
        private double XAF;
        private double XAG;
        private double XAU;
        private double XCD;
        private double XDR;
        private double XOF;
        private double XPF;
        private double YER;
        private double ZAR;
        private double ZMK;
        private double ZMW;
        private double ZWL;

        public double getAED() {
            return AED;
        }

        public void setAED(double AED) {
            this.AED = AED;
        }

        public double getAFN() {
            return AFN;
        }

        public void setAFN(double AFN) {
            this.AFN = AFN;
        }

        public double getALL() {
            return ALL;
        }

        public void setALL(double ALL) {
            this.ALL = ALL;
        }

        public double getAMD() {
            return AMD;
        }

        public void setAMD(double AMD) {
            this.AMD = AMD;
        }

        public double getANG() {
            return ANG;
        }

        public void setANG(double ANG) {
            this.ANG = ANG;
        }

        public double getAOA() {
            return AOA;
        }

        public void setAOA(double AOA) {
            this.AOA = AOA;
        }

        public double getARS() {
            return ARS;
        }

        public void setARS(double ARS) {
            this.ARS = ARS;
        }

        public double getAUD() {
            return AUD;
        }

        public void setAUD(double AUD) {
            this.AUD = AUD;
        }

        public double getAWG() {
            return AWG;
        }

        public void setAWG(double AWG) {
            this.AWG = AWG;
        }

        public double getAZN() {
            return AZN;
        }

        public void setAZN(double AZN) {
            this.AZN = AZN;
        }

        public double getBAM() {
            return BAM;
        }

        public void setBAM(double BAM) {
            this.BAM = BAM;
        }

        public double getBBD() {
            return BBD;
        }

        public void setBBD(double BBD) {
            this.BBD = BBD;
        }

        public double getBDT() {
            return BDT;
        }

        public void setBDT(double BDT) {
            this.BDT = BDT;
        }

        public double getBGN() {
            return BGN;
        }

        public void setBGN(double BGN) {
            this.BGN = BGN;
        }

        public double getBHD() {
            return BHD;
        }

        public void setBHD(double BHD) {
            this.BHD = BHD;
        }

        public double getBIF() {
            return BIF;
        }

        public void setBIF(double BIF) {
            this.BIF = BIF;
        }

        public double getBMD() {
            return BMD;
        }

        public void setBMD(double BMD) {
            this.BMD = BMD;
        }

        public double getBND() {
            return BND;
        }

        public void setBND(double BND) {
            this.BND = BND;
        }

        public double getBOB() {
            return BOB;
        }

        public void setBOB(double BOB) {
            this.BOB = BOB;
        }

        public double getBRL() {
            return BRL;
        }

        public void setBRL(double BRL) {
            this.BRL = BRL;
        }

        public double getBSD() {
            return BSD;
        }

        public void setBSD(double BSD) {
            this.BSD = BSD;
        }

        public double getBTC() {
            return BTC;
        }

        public void setBTC(double BTC) {
            this.BTC = BTC;
        }

        public double getBTN() {
            return BTN;
        }

        public void setBTN(double BTN) {
            this.BTN = BTN;
        }

        public double getBWP() {
            return BWP;
        }

        public void setBWP(double BWP) {
            this.BWP = BWP;
        }

        public double getBYN() {
            return BYN;
        }

        public void setBYN(double BYN) {
            this.BYN = BYN;
        }

        public double getBYR() {
            return BYR;
        }

        public void setBYR(double BYR) {
            this.BYR = BYR;
        }

        public double getBZD() {
            return BZD;
        }

        public void setBZD(double BZD) {
            this.BZD = BZD;
        }

        public double getCAD() {
            return CAD;
        }

        public void setCAD(double CAD) {
            this.CAD = CAD;
        }

        public double getCDF() {
            return CDF;
        }

        public void setCDF(double CDF) {
            this.CDF = CDF;
        }

        public double getCHF() {
            return CHF;
        }

        public void setCHF(double CHF) {
            this.CHF = CHF;
        }

        public double getCLF() {
            return CLF;
        }

        public void setCLF(double CLF) {
            this.CLF = CLF;
        }

        public double getCLP() {
            return CLP;
        }

        public void setCLP(double CLP) {
            this.CLP = CLP;
        }

        public double getCNY() {
            return CNY;
        }

        public void setCNY(double CNY) {
            this.CNY = CNY;
        }

        public double getCOP() {
            return COP;
        }

        public void setCOP(double COP) {
            this.COP = COP;
        }

        public double getCRC() {
            return CRC;
        }

        public void setCRC(double CRC) {
            this.CRC = CRC;
        }

        public double getCUC() {
            return CUC;
        }

        public void setCUC(double CUC) {
            this.CUC = CUC;
        }

        public double getCUP() {
            return CUP;
        }

        public void setCUP(double CUP) {
            this.CUP = CUP;
        }

        public double getCVE() {
            return CVE;
        }

        public void setCVE(double CVE) {
            this.CVE = CVE;
        }

        public double getCZK() {
            return CZK;
        }

        public void setCZK(double CZK) {
            this.CZK = CZK;
        }

        public double getDJF() {
            return DJF;
        }

        public void setDJF(double DJF) {
            this.DJF = DJF;
        }

        public double getDKK() {
            return DKK;
        }

        public void setDKK(double DKK) {
            this.DKK = DKK;
        }

        public double getDOP() {
            return DOP;
        }

        public void setDOP(double DOP) {
            this.DOP = DOP;
        }

        public double getDZD() {
            return DZD;
        }

        public void setDZD(double DZD) {
            this.DZD = DZD;
        }

        public double getEGP() {
            return EGP;
        }

        public void setEGP(double EGP) {
            this.EGP = EGP;
        }

        public double getERN() {
            return ERN;
        }

        public void setERN(double ERN) {
            this.ERN = ERN;
        }

        public double getETB() {
            return ETB;
        }

        public void setETB(double ETB) {
            this.ETB = ETB;
        }

        public int getEUR() {
            return EUR;
        }

        public void setEUR(int EUR) {
            this.EUR = EUR;
        }

        public double getFJD() {
            return FJD;
        }

        public void setFJD(double FJD) {
            this.FJD = FJD;
        }

        public double getFKP() {
            return FKP;
        }

        public void setFKP(double FKP) {
            this.FKP = FKP;
        }

        public double getGBP() {
            return GBP;
        }

        public void setGBP(double GBP) {
            this.GBP = GBP;
        }

        public double getGEL() {
            return GEL;
        }

        public void setGEL(double GEL) {
            this.GEL = GEL;
        }

        public double getGGP() {
            return GGP;
        }

        public void setGGP(double GGP) {
            this.GGP = GGP;
        }

        public double getGHS() {
            return GHS;
        }

        public void setGHS(double GHS) {
            this.GHS = GHS;
        }

        public double getGIP() {
            return GIP;
        }

        public void setGIP(double GIP) {
            this.GIP = GIP;
        }

        public double getGMD() {
            return GMD;
        }

        public void setGMD(double GMD) {
            this.GMD = GMD;
        }

        public double getGNF() {
            return GNF;
        }

        public void setGNF(double GNF) {
            this.GNF = GNF;
        }

        public double getGTQ() {
            return GTQ;
        }

        public void setGTQ(double GTQ) {
            this.GTQ = GTQ;
        }

        public double getGYD() {
            return GYD;
        }

        public void setGYD(double GYD) {
            this.GYD = GYD;
        }

        public double getHKD() {
            return HKD;
        }

        public void setHKD(double HKD) {
            this.HKD = HKD;
        }

        public double getHNL() {
            return HNL;
        }

        public void setHNL(double HNL) {
            this.HNL = HNL;
        }

        public double getHRK() {
            return HRK;
        }

        public void setHRK(double HRK) {
            this.HRK = HRK;
        }

        public double getHTG() {
            return HTG;
        }

        public void setHTG(double HTG) {
            this.HTG = HTG;
        }

        public double getHUF() {
            return HUF;
        }

        public void setHUF(double HUF) {
            this.HUF = HUF;
        }

        public double getIDR() {
            return IDR;
        }

        public void setIDR(double IDR) {
            this.IDR = IDR;
        }

        public double getILS() {
            return ILS;
        }

        public void setILS(double ILS) {
            this.ILS = ILS;
        }

        public double getIMP() {
            return IMP;
        }

        public void setIMP(double IMP) {
            this.IMP = IMP;
        }

        public double getINR() {
            return INR;
        }

        public void setINR(double INR) {
            this.INR = INR;
        }

        public double getIQD() {
            return IQD;
        }

        public void setIQD(double IQD) {
            this.IQD = IQD;
        }

        public double getIRR() {
            return IRR;
        }

        public void setIRR(double IRR) {
            this.IRR = IRR;
        }

        public double getISK() {
            return ISK;
        }

        public void setISK(double ISK) {
            this.ISK = ISK;
        }

        public double getJEP() {
            return JEP;
        }

        public void setJEP(double JEP) {
            this.JEP = JEP;
        }

        public double getJMD() {
            return JMD;
        }

        public void setJMD(double JMD) {
            this.JMD = JMD;
        }

        public double getJOD() {
            return JOD;
        }

        public void setJOD(double JOD) {
            this.JOD = JOD;
        }

        public double getJPY() {
            return JPY;
        }

        public void setJPY(double JPY) {
            this.JPY = JPY;
        }

        public double getKES() {
            return KES;
        }

        public void setKES(double KES) {
            this.KES = KES;
        }

        public double getKGS() {
            return KGS;
        }

        public void setKGS(double KGS) {
            this.KGS = KGS;
        }

        public double getKHR() {
            return KHR;
        }

        public void setKHR(double KHR) {
            this.KHR = KHR;
        }

        public double getKMF() {
            return KMF;
        }

        public void setKMF(double KMF) {
            this.KMF = KMF;
        }

        public double getKPW() {
            return KPW;
        }

        public void setKPW(double KPW) {
            this.KPW = KPW;
        }

        public double getKRW() {
            return KRW;
        }

        public void setKRW(double KRW) {
            this.KRW = KRW;
        }

        public double getKWD() {
            return KWD;
        }

        public void setKWD(double KWD) {
            this.KWD = KWD;
        }

        public double getKYD() {
            return KYD;
        }

        public void setKYD(double KYD) {
            this.KYD = KYD;
        }

        public double getKZT() {
            return KZT;
        }

        public void setKZT(double KZT) {
            this.KZT = KZT;
        }

        public double getLAK() {
            return LAK;
        }

        public void setLAK(double LAK) {
            this.LAK = LAK;
        }

        public double getLBP() {
            return LBP;
        }

        public void setLBP(double LBP) {
            this.LBP = LBP;
        }

        public double getLKR() {
            return LKR;
        }

        public void setLKR(double LKR) {
            this.LKR = LKR;
        }

        public double getLRD() {
            return LRD;
        }

        public void setLRD(double LRD) {
            this.LRD = LRD;
        }

        public double getLSL() {
            return LSL;
        }

        public void setLSL(double LSL) {
            this.LSL = LSL;
        }

        public double getLTL() {
            return LTL;
        }

        public void setLTL(double LTL) {
            this.LTL = LTL;
        }

        public double getLVL() {
            return LVL;
        }

        public void setLVL(double LVL) {
            this.LVL = LVL;
        }

        public double getLYD() {
            return LYD;
        }

        public void setLYD(double LYD) {
            this.LYD = LYD;
        }

        public double getMAD() {
            return MAD;
        }

        public void setMAD(double MAD) {
            this.MAD = MAD;
        }

        public double getMDL() {
            return MDL;
        }

        public void setMDL(double MDL) {
            this.MDL = MDL;
        }

        public double getMGA() {
            return MGA;
        }

        public void setMGA(double MGA) {
            this.MGA = MGA;
        }

        public double getMKD() {
            return MKD;
        }

        public void setMKD(double MKD) {
            this.MKD = MKD;
        }

        public double getMMK() {
            return MMK;
        }

        public void setMMK(double MMK) {
            this.MMK = MMK;
        }

        public double getMNT() {
            return MNT;
        }

        public void setMNT(double MNT) {
            this.MNT = MNT;
        }

        public double getMOP() {
            return MOP;
        }

        public void setMOP(double MOP) {
            this.MOP = MOP;
        }

        public double getMRO() {
            return MRO;
        }

        public void setMRO(double MRO) {
            this.MRO = MRO;
        }

        public double getMUR() {
            return MUR;
        }

        public void setMUR(double MUR) {
            this.MUR = MUR;
        }

        public double getMVR() {
            return MVR;
        }

        public void setMVR(double MVR) {
            this.MVR = MVR;
        }

        public double getMWK() {
            return MWK;
        }

        public void setMWK(double MWK) {
            this.MWK = MWK;
        }

        public double getMXN() {
            return MXN;
        }

        public void setMXN(double MXN) {
            this.MXN = MXN;
        }

        public double getMYR() {
            return MYR;
        }

        public void setMYR(double MYR) {
            this.MYR = MYR;
        }

        public double getMZN() {
            return MZN;
        }

        public void setMZN(double MZN) {
            this.MZN = MZN;
        }

        public double getNAD() {
            return NAD;
        }

        public void setNAD(double NAD) {
            this.NAD = NAD;
        }

        public double getNGN() {
            return NGN;
        }

        public void setNGN(double NGN) {
            this.NGN = NGN;
        }

        public double getNIO() {
            return NIO;
        }

        public void setNIO(double NIO) {
            this.NIO = NIO;
        }

        public double getNOK() {
            return NOK;
        }

        public void setNOK(double NOK) {
            this.NOK = NOK;
        }

        public double getNPR() {
            return NPR;
        }

        public void setNPR(double NPR) {
            this.NPR = NPR;
        }

        public double getNZD() {
            return NZD;
        }

        public void setNZD(double NZD) {
            this.NZD = NZD;
        }

        public double getOMR() {
            return OMR;
        }

        public void setOMR(double OMR) {
            this.OMR = OMR;
        }

        public double getPAB() {
            return PAB;
        }

        public void setPAB(double PAB) {
            this.PAB = PAB;
        }

        public double getPEN() {
            return PEN;
        }

        public void setPEN(double PEN) {
            this.PEN = PEN;
        }

        public double getPGK() {
            return PGK;
        }

        public void setPGK(double PGK) {
            this.PGK = PGK;
        }

        public double getPHP() {
            return PHP;
        }

        public void setPHP(double PHP) {
            this.PHP = PHP;
        }

        public double getPKR() {
            return PKR;
        }

        public void setPKR(double PKR) {
            this.PKR = PKR;
        }

        public double getPLN() {
            return PLN;
        }

        public void setPLN(double PLN) {
            this.PLN = PLN;
        }

        public double getPYG() {
            return PYG;
        }

        public void setPYG(double PYG) {
            this.PYG = PYG;
        }

        public double getQAR() {
            return QAR;
        }

        public void setQAR(double QAR) {
            this.QAR = QAR;
        }

        public double getRON() {
            return RON;
        }

        public void setRON(double RON) {
            this.RON = RON;
        }

        public double getRSD() {
            return RSD;
        }

        public void setRSD(double RSD) {
            this.RSD = RSD;
        }

        public double getRUB() {
            return RUB;
        }

        public void setRUB(double RUB) {
            this.RUB = RUB;
        }

        public double getRWF() {
            return RWF;
        }

        public void setRWF(double RWF) {
            this.RWF = RWF;
        }

        public double getSAR() {
            return SAR;
        }

        public void setSAR(double SAR) {
            this.SAR = SAR;
        }

        public double getSBD() {
            return SBD;
        }

        public void setSBD(double SBD) {
            this.SBD = SBD;
        }

        public double getSCR() {
            return SCR;
        }

        public void setSCR(double SCR) {
            this.SCR = SCR;
        }

        public double getSDG() {
            return SDG;
        }

        public void setSDG(double SDG) {
            this.SDG = SDG;
        }

        public double getSEK() {
            return SEK;
        }

        public void setSEK(double SEK) {
            this.SEK = SEK;
        }

        public double getSGD() {
            return SGD;
        }

        public void setSGD(double SGD) {
            this.SGD = SGD;
        }

        public double getSHP() {
            return SHP;
        }

        public void setSHP(double SHP) {
            this.SHP = SHP;
        }

        public double getSLL() {
            return SLL;
        }

        public void setSLL(double SLL) {
            this.SLL = SLL;
        }

        public double getSOS() {
            return SOS;
        }

        public void setSOS(double SOS) {
            this.SOS = SOS;
        }

        public double getSRD() {
            return SRD;
        }

        public void setSRD(double SRD) {
            this.SRD = SRD;
        }

        public double getSTD() {
            return STD;
        }

        public void setSTD(double STD) {
            this.STD = STD;
        }

        public double getSVC() {
            return SVC;
        }

        public void setSVC(double SVC) {
            this.SVC = SVC;
        }

        public double getSYP() {
            return SYP;
        }

        public void setSYP(double SYP) {
            this.SYP = SYP;
        }

        public double getSZL() {
            return SZL;
        }

        public void setSZL(double SZL) {
            this.SZL = SZL;
        }

        public double getTHB() {
            return THB;
        }

        public void setTHB(double THB) {
            this.THB = THB;
        }

        public double getTJS() {
            return TJS;
        }

        public void setTJS(double TJS) {
            this.TJS = TJS;
        }

        public double getTMT() {
            return TMT;
        }

        public void setTMT(double TMT) {
            this.TMT = TMT;
        }

        public double getTND() {
            return TND;
        }

        public void setTND(double TND) {
            this.TND = TND;
        }

        public double getTOP() {
            return TOP;
        }

        public void setTOP(double TOP) {
            this.TOP = TOP;
        }

        public double getTRY() {
            return TRY;
        }

        public void setTRY(double TRY) {
            this.TRY = TRY;
        }

        public double getTTD() {
            return TTD;
        }

        public void setTTD(double TTD) {
            this.TTD = TTD;
        }

        public double getTWD() {
            return TWD;
        }

        public void setTWD(double TWD) {
            this.TWD = TWD;
        }

        public double getTZS() {
            return TZS;
        }

        public void setTZS(double TZS) {
            this.TZS = TZS;
        }

        public double getUAH() {
            return UAH;
        }

        public void setUAH(double UAH) {
            this.UAH = UAH;
        }

        public double getUGX() {
            return UGX;
        }

        public void setUGX(double UGX) {
            this.UGX = UGX;
        }

        public double getUSD() {
            return USD;
        }

        public void setUSD(double USD) {
            this.USD = USD;
        }

        public double getUYU() {
            return UYU;
        }

        public void setUYU(double UYU) {
            this.UYU = UYU;
        }

        public double getUZS() {
            return UZS;
        }

        public void setUZS(double UZS) {
            this.UZS = UZS;
        }

        public double getVEF() {
            return VEF;
        }

        public void setVEF(double VEF) {
            this.VEF = VEF;
        }

        public double getVND() {
            return VND;
        }

        public void setVND(double VND) {
            this.VND = VND;
        }

        public double getVUV() {
            return VUV;
        }

        public void setVUV(double VUV) {
            this.VUV = VUV;
        }

        public double getWST() {
            return WST;
        }

        public void setWST(double WST) {
            this.WST = WST;
        }

        public double getXAF() {
            return XAF;
        }

        public void setXAF(double XAF) {
            this.XAF = XAF;
        }

        public double getXAG() {
            return XAG;
        }

        public void setXAG(double XAG) {
            this.XAG = XAG;
        }

        public double getXAU() {
            return XAU;
        }

        public void setXAU(double XAU) {
            this.XAU = XAU;
        }

        public double getXCD() {
            return XCD;
        }

        public void setXCD(double XCD) {
            this.XCD = XCD;
        }

        public double getXDR() {
            return XDR;
        }

        public void setXDR(double XDR) {
            this.XDR = XDR;
        }

        public double getXOF() {
            return XOF;
        }

        public void setXOF(double XOF) {
            this.XOF = XOF;
        }

        public double getXPF() {
            return XPF;
        }

        public void setXPF(double XPF) {
            this.XPF = XPF;
        }

        public double getYER() {
            return YER;
        }

        public void setYER(double YER) {
            this.YER = YER;
        }

        public double getZAR() {
            return ZAR;
        }

        public void setZAR(double ZAR) {
            this.ZAR = ZAR;
        }

        public double getZMK() {
            return ZMK;
        }

        public void setZMK(double ZMK) {
            this.ZMK = ZMK;
        }

        public double getZMW() {
            return ZMW;
        }

        public void setZMW(double ZMW) {
            this.ZMW = ZMW;
        }

        public double getZWL() {
            return ZWL;
        }

        public void setZWL(double ZWL) {
            this.ZWL = ZWL;
        }
    }
}
