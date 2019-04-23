package br.com.newsoftwarebrasil.praticandobusca.util

class DateUtil {
    companion object {
        /**
         * Method return formatted date
         */
        fun getFormattedDate(date: String?): String {
            var dateArr: List<String>?
            dateArr = date?.split("-")
            return (dateArr?.get(2) ?: "99") + "/" + (dateArr?.get(1) ?: "99") + "/" + (dateArr?.get(0) ?: "99")
        }
    }
}
