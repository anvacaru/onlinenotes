const chai = require('chai');
const chaiHttp = require('chai-http');
const server = 'http://localhost:8080';
const should = chai.should();
const formData = require('form-data');

chai.use(chaiHttp);

describe('/DELETE delete note', () => {
    it('Should return 405 when trying to delete without a note param', (done) => {
        chai.request(server)
                .delete('/delete')
                .end((err, res) => {
                    res.should.have.status(405);
                    done();
                });
    });

    xit('Should return 400 when deleting a not existent note', (done) => {
        chai.request(server)
                .post('/delete?url=bla')
                .end((err, res) => {
                    res.should.have.status(400);      
                    done();
                });
    });
});
