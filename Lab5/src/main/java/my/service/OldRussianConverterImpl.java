package main.java.my.service;

import javax.jws.WebService;

@WebService(endpointInterface = "main.java.my.service.OldRussianConverter")
public class OldRussianConverterImpl implements OldRussianConverter {

    @Override
    public double convert(double num, Measures from, Measures to) {

        if(from == Measures.METER)
            return fromVershok(num * 22.5, to);
        if (from.compareTo(to) == 0)
            return num;
        switch (from) {
            case TOCHKA:
                return fromTochka(num, to);
            case LINIA:
                return fromLinia(num, to);
            case VERSHOK:
                return fromVershok(num, to);
            case PYAD:
                return fromPyad(num, to);
            case ARSHIN:
                return fromArshin(num, to);
            case SAZHEN:
                return fromSazhen(num, to);
            case VERSTA:
                return fromVersta(num, to);
        }
        return 0;
    }

    double fromTochka(double tochka, Measures to) {
        if (to == Measures.TOCHKA)
            return tochka;
        else
            return fromLinia(tochka / 10.0, to);
    }

    double fromLinia(double linia, Measures to) {
        switch (to) {
            case TOCHKA:
                return fromTochka(linia * 10.0, to);
            case LINIA:
                return linia;
        }
        return fromVershok(linia / 17.5, to);
    }

    double fromVershok(double vershok, Measures to) {
        switch (to) {
            case TOCHKA:
            case LINIA:
                return fromLinia(vershok * 17.5, to);
            case VERSHOK:
                return vershok;
            case PYAD:
                return fromPyad(vershok / 4, to);
            case METER:
                return vershok / 22.5;
        }
        return fromArshin(vershok / 16, to);
    }

    double fromPyad(double pyad, Measures to) {
        if (to == Measures.PYAD)
            return pyad;
        else
            return fromVershok(pyad * 4, to);
    }

    double fromArshin(double arshin, Measures to) {
        switch (to) {
            case TOCHKA:
            case LINIA:
            case VERSHOK:
            case PYAD:
            case METER:
                return fromVershok(arshin * 16, to);
            case ARSHIN:
                return arshin;
        }
        return fromSazhen(arshin / 3, to);
    }

    double fromSazhen(double sazhen, Measures to) {
        switch (to) {
            case TOCHKA:
            case LINIA:
            case VERSHOK:
            case PYAD:
            case ARSHIN:
            case METER:
                return fromArshin(sazhen * 3, to);
            case SAZHEN:
                return sazhen;
        }
        return fromVersta(sazhen / 500, to);
    }

    double fromVersta(double versta, Measures to) {
        if (to == Measures.VERSTA)
            return versta;
        else
            return fromSazhen(versta * 500, to);
    }
}