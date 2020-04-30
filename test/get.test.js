const expect = require('chai').expect;
const request = require('request');
const server = 'http://localhost:8080';
describe('Main page', () => {
    it('Main page status', (done) => {
        request(server, function (error, response, body) {
            expect(response.statusCode).to.equal(200);
            done();
        });
    });
    it('Should return 404 for a not existent page', (done) => {
        request(server + "/invalidPage", function (error, response, body) {
            expect(response.statusCode).to.equal(404);
            done();
        });
    });
});

describe('Retrieving a note', () => {
    it('Should return 200 when accessing a public note', (done) => {
        request(server + '/view?v=giZIrfQr', function (error, response, body) {
            expect(response.statusCode).to.equal(200);
            done();
        });
    });

    it('Should return 404 when accessing a not existent note', (done) => {
        request(server + '/view?v=abcInvalid', function (error, response, body) {
            expect(response.statusCode).to.equal(404);
            done();
        });
    });
});
