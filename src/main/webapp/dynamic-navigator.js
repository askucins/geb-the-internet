var app = new Vue({
    el: '#app',
    data: {
        berries: [
            'strawberry',
            'raspberry',
            'blueberry',
            'cranberry'
        ]
    },
    methods: {
        swapWithNext: function (index) {
            this.berries.splice(index, 2, this.berries[index + 1], this.berries[index])
        },
        hello: function (index) {
            console.log("On position: " + index +", value: "+ this.berries[index])
        }
    }
});