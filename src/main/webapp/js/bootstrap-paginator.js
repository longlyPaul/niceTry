/**
 * bootstrap-paginator.js v0.5
 * --
 * Copyright 2013 Yun Lai <lyonlai1984@gmail.com>
 * --
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

(function ($) {

    "use strict"; // jshint ;_;


    /* Paginator PUBLIC CLASS DEFINITION
     * ================================= */

    /**
     * Boostrap Paginator Constructor
     *
     * @param element element of the paginator
     * @param options the options to config the paginator
     *
     * */
    var BootstrapPaginator = function (element, options) {
        this.init(element, options);
    },
        old = null;

    BootstrapPaginator.prototype = {

        /**
         * Initialization function of the paginator, accepting an element and the options as parameters
         *
         * @param element element of the paginator
         * @param options the options to config the paginator
         *
         * */
        init: function (element, options) {

            this.$element = $(element);

            this.currentPage = 1;

            this.lastPage = 1;

            this.setOptions(options);

            this.initialized = true;
        },

        /**
         * Update the properties of the paginator element
         *
         * @param options options to config the paginator
         * */
        setOptions: function (options) {

            this.options = $.extend({}, (this.options || $.fn.bootstrapPaginator.defaults), options);

            this.totalPages = parseInt(this.options.totalPages, 10);  //设置总页数
            this.numberOfPages = parseInt(this.options.numberOfPages, 10); //设置每页显示的个数

            //move the set current page after the setting of total pages. otherwise it will cause out of page exception.
            if (options && typeof (options.currentPage)  !== 'undefined') {

                this.setCurrentPage(options.currentPage);
            }

            this.listen();//绑定page-clicked、page-changed事件

            this.render();//渲染组件

            if (!this.initialized && this.lastPage !== this.currentPage) {
                this.$element.trigger("page-changed", [this.lastPage, this.currentPage]);
            }

        },

        /**
         * Sets up the events listeners. Currently the pageclicked and pagechanged events are linked if available.
         *
         * */
        listen: function () {

            this.$element.off("page-clicked");

            this.$element.off("page-changed");// unload the events for the element

            if (typeof (this.options.onPageClicked) === "function") {
                this.$element.bind("page-clicked", this.options.onPageClicked);
            }

            if (typeof (this.options.onPageChanged) === "function") {
                this.$element.on("page-changed", this.options.onPageChanged);
            }

        },


        /**
         *
         *  Destroys the paginator element, it unload the event first, then empty the content inside.
         *
         * */
        destroy: function () {

            this.$element.off("page-clicked");

            this.$element.off("page-changed");

            $.removeData(this.$element, 'bootstrapPaginator');

            this.$element.empty();

        },

        /**
         * Shows the page
         *
         * */
        show: function (page) {

            this.setCurrentPage(page);

            this.render();

            if (this.lastPage !== this.currentPage) {
                this.$element.trigger("page-changed", [this.lastPage, this.currentPage]);
            }
        },

        /**
         * Shows the next page
         *
         * */
        showNext: function () {
            var pages = this.getPages();

            if (pages.next) {
                this.show(pages.next);
            }

        },

        /**
         * Shows the previous page
         *
         * */
        showPrevious: function () {
            var pages = this.getPages();

            if (pages.prev) {
                this.show(pages.prev);
            }

        },

        /**
         * Shows the first page
         *
         * */
        showFirst: function () {
            var pages = this.getPages();

            if (pages.first) {
                this.show(pages.first);
            }

        },

        /**
         * Shows the last page
         *
         * */
        showLast: function () {
            var pages = this.getPages();

            if (pages.last) {
                this.show(pages.last);
            }

        },

        /**
         * Internal on page item click handler, when the page item is clicked, change the current page to the corresponding page and
         * trigger the pageclick event for the listeners.
         *
         *
         * */
        onPageItemClicked: function (event) {

            var type = event.data.type,
                page = event.data.page;

            //show the corresponding page and retrieve the newly built item related to the page clicked before for the event return
            switch (type) {

            case "first":
                this.showFirst();
                break;
            case "prev":
                this.showPrevious();
                break;
            case "next":
                this.showNext();
                break;
            case "last":
                this.showLast();
                break;
            case "page":
                this.show(page);
                break;
            }
            this.$element.trigger("page-clicked", [type, page]);
        },

        /**
         * Renders the paginator according to the internal properties and the settings.
         *
         *
         * */
        render: function () {

            //fetch the container class and add them to the container
            var containerClass = this.getValueFromOption(this.options.containerClass, this.$element),
                size = this.options.size || "normal",
                alignment = this.options.alignment || "left",
                pages = this.getPages(),
                //listContainer = $("<ul></ul>"),
                //listContainerClass =  this.getValueFromOption(this.options.listContainerClass, listContainer),
                first = null,
                prev = null,
                next = null,
                last = null,
                p = null,
                i = 0;


            this.$element.prop("class", "");

            this.$element.addClass("pagination");

            switch (size.toLowerCase()) {
            case "large":
                this.$element.addClass("pagination-large");
                break;
            case "small":
                this.$element.addClass("pagination-small");
                break;
            case "mini":
                this.$element.addClass("pagination-mini");
                break;
            default:
                break;
            }

            switch (alignment.toLowerCase()) {

            case "center":
                this.$element.addClass("pagination-centered");
                break;
            case "right":
                this.$element.addClass("pagination-right");
                break;
            default:
                break;

            }

            this.$element.addClass(containerClass);

            //empty the outter most container then add the listContainer inside.
            this.$element.empty();

            //this.$element.append(listContainer);
            //
            //listContainer.addClass(listContainerClass);

            //update the page element reference
            this.pageRef = [];

            first = this.buildPageItem("first", pages.first);

            if (first) {
                this.$element.append(first);
            }

            prev = this.buildPageItem("prev", pages.prev);

            if (prev) {
                this.$element.append(prev);
            }

            if(pages[0]>1){
                p = this.buildPageItem("page", 1);
                if (p) {
                    this.$element.append(p);
                }
                this.$element.append(this.buildBreakItem());
            }
            for (i = 0; i < pages.length; i = i + 1) {//fill the numeric pages.

                p = this.buildPageItem("page", pages[i]);

                if (p) {
                    this.$element.append(p);
                }
            }
            if(pages[pages.length-1]<this.totalPages){
                this.$element.append(this.buildBreakItem());
                p = this.buildPageItem("page", this.totalPages);
                if (p) {
                    this.$element.append(p);
                }
            }
            next = this.buildPageItem("next", pages.next);

            if (next) {
                this.$element.append(next);
            }

            last = this.buildPageItem("last", pages.last);

            if (last) {
                this.$element.append(last);
            }

            this.$element.append('<span class="page-skip">共'+this.totalPages+'页，到第<input type="text" class="page-num" id="page-num">页<button type="button" class="btn_key page-submit" name="page_submit" id="page_submit">确定</button></span>');
            var $this=this;
            $('#page_submit').click(function(){
                var p=$('#page-num').val();
                if(p.length==0)return;
                $this.show(p);
                $this.$element.trigger("page-clicked", ['page', p]);
            });
            $('#page-num').on('keyup',function(){
                var p=parseInt($('#page-num').val());
                if(isNaN(p)){
                    $(this).val('');
                }else{
                    if(p<1)p=1;
                    if(p>$this.totalPages)p=$this.totalPages;
                    $(this).val(p);
                }
            });
        },

        /**
         *
         * Creates a page item base on the type and page number given.
         *
         * @param page page number
         * @param type type of the page, whether it is the first, prev, page, next, last
         *
         * @return Object the constructed page element
         * */
        buildPageItem: function (type, page) {

            //var itemContainer = $("<li></li>"),//creates the item container
            var itemContent = $("<a></a>"),//creates the item content
                text = "",
                title = "",
                itemContainerClass = this.options.itemContainerClass(type, page, this.currentPage),
                itemContentClass = this.getValueFromOption(this.options.itemContentClass, type, page, this.currentPage),
                tooltipOpts = null;


            switch (type) {

            case "first":
                if (!this.getValueFromOption(this.options.shouldShowPage, type, page, this.currentPage)) { return; }
                text = this.options.itemTexts(type, page, this.currentPage);
                title = this.options.tooltipTitles(type, page, this.currentPage);
                break;
            case "last":
                if (!this.getValueFromOption(this.options.shouldShowPage, type, page, this.currentPage)) { return; }
                text = this.options.itemTexts(type, page, this.currentPage);
                title = this.options.tooltipTitles(type, page, this.currentPage);
                break;
            case "prev":
                if (!this.getValueFromOption(this.options.shouldShowPage, type, page, this.currentPage)) { return; }
                text = this.options.itemTexts(type, page, this.currentPage);
                title = this.options.tooltipTitles(type, page, this.currentPage);
                itemContent.addClass('page-prev');
                break;
            case "next":
                if (!this.getValueFromOption(this.options.shouldShowPage, type, page, this.currentPage)) { return; }
                text = this.options.itemTexts(type, page, this.currentPage);
                title = this.options.tooltipTitles(type, page, this.currentPage);
                itemContent.addClass('page-next');
                break;
            case "page":
                if (!this.getValueFromOption(this.options.shouldShowPage, type, page, this.currentPage)) { return; }
                text = this.options.itemTexts(type, page, this.currentPage);
                title = this.options.tooltipTitles(type, page, this.currentPage);
                break;
            }

            itemContent.addClass(itemContainerClass);

            itemContent.addClass(itemContentClass).html(text);

            if(page===null){
                itemContent.addClass('dis');
            }else{
                itemContent.on("click", null, {type: type, page: page}, $.proxy(this.onPageItemClicked, this));
            }
            if (this.options.pageUrl) {
                itemContent.attr("href", this.getValueFromOption(this.options.pageUrl, type, page, this.currentPage));
            }

            itemContent.attr("title", title);

            return itemContent;

        },

        buildBreakItem:function(){
            return '<span class="page-break">...</span>';
        },

        setCurrentPage: function (page) {
            if (page > this.totalPages || page < 1) {// if the current page is out of range, throw exception.

                throw "Page out of range";

            }

            this.lastPage = this.currentPage;

            this.currentPage = parseInt(page, 10);

        },

        /**
         * Gets an array that represents the current status of the page object. Numeric pages can be access via array mode. length attributes describes how many numeric pages are there. First, previous, next and last page can be accessed via attributes first, prev, next and last. Current attribute marks the current page within the pages.
         * 获取表示当前页面状态的数组。
         * @return object output objects that has first, prev, next, last and also the number of pages in between.
         * */
        getPages: function () {

            var totalPages = this.totalPages,// get or calculate the total pages via the total records
                pageStart = (this.currentPage % this.numberOfPages === 0) ? (parseInt(this.currentPage / this.numberOfPages, 10) - 1) * this.numberOfPages + 1 : parseInt(this.currentPage / this.numberOfPages, 10) * this.numberOfPages + 1,//计算起始页。如，每页5条，则当前在1~5页时，起始页为1，当前在6~10页时，起始页为6
                output = [],
                i = 0,
                counter = 0;

            pageStart = pageStart < 1 ? 1 : pageStart;//check the range of the page start to see if its less than 1.

            for (i = pageStart, counter = 0; counter < this.numberOfPages && i <= totalPages; i = i + 1, counter = counter + 1) {//填充。可能为[6,7,8,9,10]（共11页）或[6,7]（共7页）
                output.push(i);
            }

            if (this.currentPage > 1) {//如果当前页不是第一页，则first属性有值（第一页）
                output.first = 1;
            } else {
                output.first = null;
            }

            if (this.currentPage > 1) {//如果当前也不是第一页，则prev属性有值（当前页-1）
                output.prev = this.currentPage - 1;
            } else {
                output.prev = null;
            }

            if (this.currentPage < totalPages) {//如果当前页不是最后一页，则next属性有值（当前页+1）
                output.next = this.currentPage + 1;
            } else {
                output.next = null;
            }

            if (this.currentPage < totalPages) {//如果当前页不是最后一页，则last属性有值（总页数）
                output.last = totalPages;
            } else {
                output.last = null;
            }

            output.current = this.currentPage;//mark the current page.

            output.total = totalPages;

            output.numberOfPages = this.options.numberOfPages;

            return output;

        },

        /**
         * Gets the value from the options, this is made to handle the situation where value is the return value of a function.
         *
         * @return mixed value that depends on the type of parameters, if the given parameter is a function, then the evaluated result is returned. Otherwise the parameter itself will get returned.
         * */
        getValueFromOption: function (value) {

            var output = null,
                args = Array.prototype.slice.call(arguments, 1);

            if (typeof value === 'function') {
                output = value.apply(this, args);
            } else {
                output = value;
            }

            return output;

        }

    };


    /* TYPEAHEAD PLUGIN DEFINITION
     * =========================== */

    old = $.fn.bootstrapPaginator;

    $.fn.bootstrapPaginator = function (option) {

        var args = arguments,
            result = null;

        $(this).each(function (index, item) {
            var $this = $(item),
                data = $this.data('bootstrapPaginator'),
                options = (typeof option !== 'object') ? null : option;

            if (!data) {
                $this.data('bootstrapPaginator', (data = new BootstrapPaginator(this, options)));
                return;
            }

            if (typeof option === 'string') {

                if (data[option]) {
                    result = data[option].apply(data, Array.prototype.slice.call(args, 1));
                } else {
                    throw "Method " + option + " does not exist";
                }

            } else {
                result = data.setOptions(option);
            }
        });

        return result;

    };

    $.fn.bootstrapPaginator.defaults = {
        containerClass: "",
        size: "normal",
        alignment: "left",
        itemContainerClass: function (type, page, current) {
            return (page === current) ? "page-cur" : "";
        },
        itemContentClass: function (type, page, current) {
            return "";
        },
        currentPage: 1,
        numberOfPages: 5,
        totalPages: 1,
        pageUrl: function (type, page, current) {
            return null;
        },
        onPageClicked: null,
        onPageChanged: null,
        useBootstrapTooltip: false,
        shouldShowPage: function(type, page, current){
            switch(type){
                case "first":
                case "last":
                    return false;
                default:
                    return true;
            }
        },

        itemTexts: function (type, page, current) {
            switch (type) {
            case "first":
                return "&lt;&lt;";
            case "prev":
                return "&lt;";
            case "next":
                return "&gt;";
            case "last":
                return "&gt;&gt;";
            case "page":
                return page;
            }
        },
        tooltipTitles: function (type, page, current) {

            switch (type) {
            case "first":
                return "首页";
            case "prev":
                return "上一页";
            case "next":
                return "下一页";
            case "last":
                return "末页";
            case "page":
                return (page === current) ? "当前在第" + page +'页': "跳转到第" + page +'页';
            }
        },
        bootstrapTooltipOptions: {
            animation: true,
            html: true,
            placement: 'top',
            selector: false,
            title: "",
            container: false
        }
    };

    //$.fn.bootstrapPaginator.Constructor = BootstrapPaginator;



}(window.jQuery));
