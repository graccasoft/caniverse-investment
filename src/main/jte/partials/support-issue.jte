@param io.caniverse.investment.model.entity.SupportIssue supportIssue


<h2 class="text-title-md2 font-bold text-black dark:text-white mb-4">${supportIssue.getSubject()}</h2>
<div class="rounded-sm border border-stroke bg-white shadow-default dark:border-strokedark dark:bg-boxdark">
    <div class="grid grid-cols-6 border-t border-stroke px-4 py-4.5 dark:border-strokedark sm:grid-cols-8 md:px-6 2xl:px-7.5">
        <div class="col-span-3 flex items-center">
            <p class="font-medium">${io.caniverse.investment.utils.WebUtils.formatDate(supportIssue.getCreatedOn())}</p>
        </div>
        <div class="col-span-3 text-right items-center">
            <p class="font-medium">${ supportIssue.getStatus() }</p>
        </div>
    </div>
    <div class="px-4 py-6 md:px-6 xl:px-7.5 text-right">
        ${supportIssue.getDescription()}
    </div>
</div>


@for(var response : supportIssue.getResponses())
    <div class="rounded-sm border border-stroke bg-white shadow-default dark:border-strokedark dark:bg-boxdark">
        <div class="grid grid-cols-2 gap-4 px-4">
            <div class="col-span-1  items-center">
                <p class="font-medium">${ io.caniverse.investment.utils.WebUtils.formatDate(response.getCreatedOn()) }</p>
            </div>
            <div class="col-span-1 text-right items-center">
                <p class="font-medium">${ response.isFromAdmin() ? "Admin" : "Investor" }</p>
            </div>
        </div>
        <div class="px-4 py-4 dark:text-textdark md:px-6 2xl:px-7.5">
            <p>${response.getResponse()}</p>
        </div>
    </div>
@endfor
