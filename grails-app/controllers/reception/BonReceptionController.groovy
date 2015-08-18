package reception

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class BonReceptionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond BonReception.list(params), model:[bonReceptionCount: BonReception.count()]
    }

    def show(BonReception bonReception) {
        respond bonReception
    }

    def create() {
        respond new BonReception(params)
    }

    @Transactional
    def save(BonReception bonReception) {
        if (bonReception == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (bonReception.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond bonReception.errors, view:'create'
            return
        }

        bonReception.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'bonReception.label', default: 'BonReception'), bonReception.id])
                redirect bonReception
            }
            '*' { respond bonReception, [status: CREATED] }
        }
    }

    def edit(BonReception bonReception) {
        respond bonReception
    }

    @Transactional
    def update(BonReception bonReception) {
        if (bonReception == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (bonReception.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond bonReception.errors, view:'edit'
            return
        }

        bonReception.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'bonReception.label', default: 'BonReception'), bonReception.id])
                redirect bonReception
            }
            '*'{ respond bonReception, [status: OK] }
        }
    }

    @Transactional
    def delete(BonReception bonReception) {

        if (bonReception == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        bonReception.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'bonReception.label', default: 'BonReception'), bonReception.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'bonReception.label', default: 'BonReception'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
